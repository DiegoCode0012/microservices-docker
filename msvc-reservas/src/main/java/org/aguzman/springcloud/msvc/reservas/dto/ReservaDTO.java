package org.aguzman.springcloud.msvc.reservas.dto;


import java.util.Date;



public class ReservaDTO {


	private Long id;
	
	 private String diaStart;
	 
	 
	 private String diaEnd;
	 

	 private HabitacionDTO HabitacionDTO; 
	 
	 private String estado;

	 private UserDTO userDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public org.aguzman.springcloud.msvc.reservas.dto.HabitacionDTO getHabitacionDTO() {
		return HabitacionDTO;
	}

	public void setHabitacionDTO(org.aguzman.springcloud.msvc.reservas.dto.HabitacionDTO habitacionDTO) {
		HabitacionDTO = habitacionDTO;
	}

	public String getDiaEnd() {
		return diaEnd;
	}

	public void setDiaEnd(String diaEnd) {
		this.diaEnd = diaEnd;
	}

	public String getDiaStart() {
		return diaStart;
	}

	public void setDiaStart(String diaStart) {
		this.diaStart = diaStart;
	}
}
