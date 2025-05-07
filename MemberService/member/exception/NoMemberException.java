package member.exception;

public class NoMemberException extends Exception {
	
	public NoMemberException() {
		
	}
	
	public NoMemberException(int memberNo) {
		super("[예외] 없는 회원입니다 : " + memberNo);
	}

}
