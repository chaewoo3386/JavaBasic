package member;
// 컨트롤 쉬프트 o 누르면 알아서 임포트
import java.util.LinkedList;
import member.exception.NoMemberException;
public class MemberService {
	// 회원정보 저장 자료구조
	private LinkedList<Member> memberList = new LinkedList<>();
	private int memberNo = 1;
	public boolean registMember(String id, String password, String username) {
		memberList.add(new Member(memberNo++, id, password, username));
		return true;
	}
	public boolean isIdValid(String id) {
		for (Member member : memberList) {
			if (member.getId().equals(id))
				return false;
		}
		return true;
	}
	public LinkedList<Member> listMembers() {
		return memberList;
	}
	public Member detailMemberInfo(int memberNo) throws NoMemberException {
		return getMember(memberNo);
	}
	private Member getMember(int memberNo)throws NoMemberException {
		for (Member member : memberList) {	
			if (member.getMemberNo() == memberNo)
				return member;
		}
		
		throw new NoMemberException(memberNo);
	}
	public boolean updateMemberInfo(int memberNo, String oldPassword, String newPassword) throws NoMemberException {
		Member member = getMember(memberNo);
		if (member == null)
			return false;
		if (member.getPassword().equals(oldPassword)) {
			member.setPassword(newPassword);
			return true;
		}
		return false;
	}
	public boolean removeMember(int memberNo) throws NoMemberException {
		Member member = getMember(memberNo);
		if (member == null)
			return false;
		memberList.remove(member);
		return true;
	}
}