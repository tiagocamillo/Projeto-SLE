package fatec.sjc.documentation;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.core.Application;

@OpenAPIDefinition(
	    info = @Info(
	        title = "API - Projeto SLE",
	        version = "1.0.1"
	        )
	)
public class CustomAPI extends Application {

}
