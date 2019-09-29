package com.richard.food.domain.model.enumerations;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CONFIRMADO("Confirmado"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");
	
	private String descricao;

	private StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	

}
