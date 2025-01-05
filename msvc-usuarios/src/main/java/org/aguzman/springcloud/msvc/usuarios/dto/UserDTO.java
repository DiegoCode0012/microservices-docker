package org.aguzman.springcloud.msvc.usuarios.dto;


import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private Long id;
	private String username;

	private String email;
	private List<ReservaDTO> reservaDTOList =new ArrayList<>();

	public List<ReservaDTO> getReservaDTOList() {
		return reservaDTOList;
	}
	public void add(ReservaDTO reservaDTO){
		reservaDTOList.add(reservaDTO);
	}
	public void setReservaDTOList(List<ReservaDTO> reservaDTOList) {
		this.reservaDTOList = reservaDTOList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", reservaDTOList=" + reservaDTOList +
				'}';
	}
}

