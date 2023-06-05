package com.hanssel.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.hanssel.demo.beans.Factoria;
import com.hanssel.demo.beans.Producto;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Factoria f1 = context.getBean(Factoria.class);
		Factoria f2 = context.getBean(Factoria.class);
		Producto p1 = context.getBean(Producto.class);
		Producto p2 = context.getBean(Producto.class);
		
		System.out.println(f1.hashCode());
		System.out.println(f2.hashCode());
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		
		
	}

}
