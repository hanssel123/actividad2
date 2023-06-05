package com.hanssel.demo.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Setter;

@Component
@Scope("prototype")
@Setter

public class Producto {
	private String idProducto;
}
