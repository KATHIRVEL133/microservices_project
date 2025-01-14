package com.kathirvel.product_service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kathirvel.product_service.dto.ProductRequest;
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.4");
	
	@Autowired
	private MockMvc mockMvc;
     
	@Autowired
	private ObjectMapper objectMapper;

	@DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry)
	{
     dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(
		MockMvcRequestBuilders
		.post("/api/product")
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
		.content(productRequestString)
		).andExpect(status().isCreated());
	}
	public ProductRequest getProductRequest()
	{
		return ProductRequest.builder()
		.name("iphone12")
		.description("iphone 12")
		.price(BigDecimal.valueOf(100000))
		.build();
	}

}
