package org.la.sse.tile.futur;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class FutureTwo {

	@Autowired
	private FutureObject futureObject;

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	public void process() {
		SseEmitter emitter = new SseEmitter();
		System.out.println("future value: " + futureObject.getNotEmpty());
		executor.execute(() -> {
			try {
				for (int i = 0; i < 4; i++) {
					emitter.send("message" + i);
				}
			} catch (Exception e) {
				emitter.completeWithError(e);
			} finally {
				emitter.complete();
			}
		});
	}
}
