
package com.conestoga.APIHousing.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@RestController
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(WebRequest request) {
        // Get the error attributes from the request
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));

                System.out.println(errorAttributes.toString());
            //print the error attributes if  they have aany
            //print error attributes in a loop
            for (Map.Entry<String, Object> entry : errorAttributes.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
                

      
        // // Construct the error response
        // StringBuilder response = new StringBuilder();
        // response.append("Error: ").append(errorMessage).append("\n\n");
        // response.append("Stack Trace:\n").append(stackTrace);

        // Return the error response with HTTP status 500
        return new ResponseEntity<>(errorAttributes.toString(),
                errorAttributes.get("status").toString().equals("404") ? HttpStatus.NOT_FOUND
                        : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
