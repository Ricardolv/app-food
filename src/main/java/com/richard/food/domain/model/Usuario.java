package com.richard.food.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@DynamicUpdate
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;

	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "usuario_grupo",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List<Grupo> grupos = new ArrayList<>();

	public boolean senhaCoincideCom(String senha) {
		return getSenha().equals(senha);
	}

	public boolean senhaNaoCoincideCom(String senha) {
		return !senhaCoincideCom(senha);
	}

	public void removerGrupo(Grupo grupo) {
		this.getGrupos().remove(grupo);
	}

	public void adicionarGrupo(Grupo grupo) {
		this.getGrupos().add(grupo);
	}
}
