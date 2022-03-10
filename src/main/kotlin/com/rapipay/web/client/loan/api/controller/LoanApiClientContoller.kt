package com.rapipay.web.client.loan.api.controller

import com.rapipay.web.client.loan.api.dto.Product
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("api/v1")
class LoanApiClientContoller {
    var webClient: WebClient = WebClient.create("https://loan-api-backend-v1.herokuapp.com")
//    var webClient: WebClient = WebClient.create("http://localhost:8080/api/v1")

    @GetMapping("/products", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getProducts(): Flux<Product> {
        return webClient.get().uri("/products")
            .retrieve()
            .bodyToFlux(Product::class.java)
            .log("products in client project")

    }

    @GetMapping("/products/{id}")
    fun getSingleProduct(@PathVariable id: Int): Mono<Product> {
        return webClient.get()
            .uri("/products" + "/" + id)
            .retrieve()
            .bodyToMono(Product::class.java)
            .log("Single product in client project")
    }

/*    @PostMapping("/products", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun saveSingleProduct(@RequestBody product: Product): Mono<Product> {
        println(product)
        val monoProduct: Mono<Product> = Mono.just(product)
        return webClient.post()
            .uri("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .body(monoProduct, Product::class.java)
            .retrieve()
            .bodyToMono(Product::class.java)
            .log()
    }*/

    @PostMapping("/products" )
    fun saveSingleProduct(@RequestBody product: Product){
        var client:WebClient = WebClient.builder()
            .baseUrl("http://localhost:8080/api/v1")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build()

        client.post()
            .uri("/products")
            .body(product, Product::class.java)
            .retrieve()
            .bodyToFlux(Product::class.java)
            .subscribe()




//        println("Hitting the post api...")
        val monoProduct: Mono<Product> = Mono.just(product)
        Mono.just(product)
            .subscribe{
                productMono -> {
                    println(productMono)
            }
            }
    }
}