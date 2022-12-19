package org.la.sse.tile.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.la.sse.tile.futur.FutureObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SSEEmitterController {
	
	@Autowired
	private FutureObject futureObject;
	
	@GetMapping("/emitter")
	public SseEmitter eventEmitter(@RequestParam String userId) {

		// 12000 here is the timeout and it is optional
		SseEmitter emitter = new SseEmitter();
		
		// create a single thread for sending messages asynchronously
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			try {
				while(true) {
					while (futureObject.getNotEmpty()) {
						TimeUnit.SECONDS.sleep(5);
					}
					if (!futureObject.getNotEmpty()) {
						for (String s : futureObject.getArray()) {
							TimeUnit.SECONDS.sleep(1);
							emitter.send("message" + s);
						}
						futureObject.clear();
					}
				}
			} catch (Exception e) {
				emitter.completeWithError(e);
			} finally {
//				emitter.complete();
			}
		});
//		executor.shutdown();
		return emitter;
	}
}
