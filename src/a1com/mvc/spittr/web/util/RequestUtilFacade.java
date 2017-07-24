package a1com.mvc.spittr.web.util;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RequestUtilFacade {
	
	public static URI getCurrentUri(HttpServletRequest request){
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromRequest(request);
		builder.scheme("http");
		URI newUri = builder.build().toUri();
		
		return newUri;
	}

}
