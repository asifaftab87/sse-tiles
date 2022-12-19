package org.la.sse.tile.configuration;

import org.la.sse.tile.futur.FutureObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/** Spring configuration for tiles. */
@Configuration
public class TilesConfiguration {

	/**	Returns the {@link UrlBasedViewResolver}. */
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	/**	Returns the {@link TilesConfigurer}. */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{ "WEB-INF/tiles.xml" });
		return tilesConfigurer;
	}
	
	@Bean
	public FutureObject futureObject() {
		return new FutureObject();
	}
}
