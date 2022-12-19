package org.la.sse.tile.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.la.sse.tile.futur.FutureObject;
import org.la.sse.tile.futur.FutureTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Rest controller, this controller will called
 * 
 */
@RestController
@RequestMapping("/sse/mvc/background")
public class BackgroundController {

	@Autowired
	private FutureObject futureObject;

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	@GetMapping(path = "/process", produces = "text/event-stream")
	public SseEmitter process() {
		SseEmitter emitter = new SseEmitter();
		
		executor.execute(() -> {
			try {
				while(true) {
					while (!futureObject.getNotEmpty()) {
						TimeUnit.SECONDS.sleep(5);
					}
					System.out.println("future empty: " + futureObject.getNotEmpty());
					if (futureObject.getNotEmpty()) {
						for (String s : futureObject.getArray()) {
							TimeUnit.SECONDS.sleep(1);
							emitter.send("message" + s);
						}
						futureObject.clear();
						break;
					}
				}
			} catch (Exception e) {
				emitter.completeWithError(e);
			} finally {
				emitter.complete();
			}
		});
		executor.shutdown();
		return emitter;
	}
}