package integration;

import com.babylon.permissions.PermissionsApplication;
import com.babylon.permissions.dao.Member;
import com.babylon.permissions.repository.MemberRepository;
import com.babylon.permissions.sysytem.NowProvider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PermissionsApplication.class})
public class MemberRepositoryTest {

  @Autowired
  private NowProvider nowProvider;

  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void save(){
    Member member = Member.builder()
        .createdAt(nowProvider.nowUtc())
        .build();
//    memberRepository.save(member);
  }
}
