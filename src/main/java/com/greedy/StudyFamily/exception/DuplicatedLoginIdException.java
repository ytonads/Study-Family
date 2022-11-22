package com.greedy.StudyFamily.exception;

						//RuntimeException은 exception의 후손!
						//RuntimeException : 동작시에 일어나는 exception에 대해 다룬다.
public class DuplicatedLoginIdException extends RuntimeException {
	
	/* exception과 RuntimeException의 차이점
	 * => exception으로 작성 시 try-catch 문이나 메소드에 throw로 명시하여야 한다.
	 * => RuntimeException으로 사용시 try-catch문을 강제화 하지 않는다. */
	
	// RuntimeExeception 시, service에서 메서드 뒤에 throws를 명시하지 않아도 된다. 
	
	public DuplicatedLoginIdException(String message) {
		super(message);
	}

}
