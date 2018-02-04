package com.example.project;

import javax.faces.annotation.FacesConfig;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.project.model.Message;
import com.example.project.model.Product;
import com.example.project.service.MessageService;
import com.example.project.service.ProductService;

@FacesConfig
@WebListener
public class ApplicationConfig implements ServletContextListener {

	@Inject
	private ProductService productService;

	@Inject
	private MessageService messageService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		enableWebsocketEndpoint(event.getServletContext());
		createTestProducts();
		createTestMessages();
	}

	private void enableWebsocketEndpoint(ServletContext context) {
		context.setInitParameter(PushContext.ENABLE_WEBSOCKET_ENDPOINT_PARAM_NAME, "true");
	}

	private void createTestProducts() {
		productService.create(Product.create("One", "The first product"));
		productService.create(Product.create("Two", "The second product"));
		productService.create(Product.create("Three", "The third product"));
	}

	private void createTestMessages() {
		Message message1 = Message.create("Lorem ipsum", null);
		Message message2 = Message.create("Dolor sit amet", message1);
		Message message3 = Message.create("Consectetur adipiscing elit", message1);
		Message message4 = Message.create("Phasellus magna mauris", message3);
		Message message5 = Message.create("Mattis vitae consectetur sit amet", message4);
		Message message6 = Message.create("Quisque vitae odio vitae", null);
		Message message7 = Message.create("Tellus tincidunt molestie in quis metus", message6);
		Message message8 = Message.create("Sed nec quam vel turpis vestibulum", message6);
		Message message9 = Message.create("Faucibus et a quam", message6);

		messageService.create(message1);
		messageService.create(message2);
		messageService.create(message3);
		messageService.create(message4);
		messageService.create(message5);
		messageService.create(message6);
		messageService.create(message7);
		messageService.create(message8);
		messageService.create(message9);
	}
}
