package com.kkh.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDTO {

	@NotBlank(message = "아이디를 입력해주세요")
	private String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String userPwd;
}
	