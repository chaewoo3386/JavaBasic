package member.file;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Map;
import member.MemberVO;
import member.HashMapMemberDAO; 

public class TextFileHashMapMemberDAO extends HashMapMemberDAO implements FileMemberDB {

    private String dataFilename = DATA_FILE + ".txt";
    private final String DATE_FORMAT = "YYYY-MM-dd HH:mm:ss"; 

    @Override
    public void saveMembers() {
        try (
            FileWriter fw = new FileWriter(dataFilename);
            PrintWriter pw = new PrintWriter(fw);
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            for (MemberVO member : memberDB.values()) { 
                pw.println(member.getAddress());
                pw.println(member.getEmail());
                pw.println(member.getId());
                pw.println(member.getMemberNo());
                pw.println(member.getMobile());
                pw.println(member.getPassword());
                pw.println(member.getUsername());
                pw.println(member.getClass()); 
                pw.println(sdf.format(member.getRegDate()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMembers() {
        try (
            FileReader fr = new FileReader(dataFilename);
            BufferedReader br = new BufferedReader(fr);
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            while (br.ready()) {
                String address = br.readLine();
                String email = br.readLine();
                String id = br.readLine();
                int memberNo = Integer.parseInt(br.readLine());
                String mobile = br.readLine();
                String password = br.readLine();
                String username = br.readLine();
                String userClass = br.readLine();
                Date regDate = sdf.parse(br.readLine());

                MemberVO member = new MemberVO(memberNo, id, password, username, mobile, email, address, regDate);
                memberDB.put(id, member);

                if (memberSeq <= memberNo) memberSeq = memberNo + 1;
            }

        } catch (FileNotFoundException e) {
            System.out.println("[로딩] " + dataFilename + "이 없습니다.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}