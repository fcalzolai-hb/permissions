package integration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.babylon.permissions.PermissionsApplication;
import com.babylon.permissions.dao.Member;
import com.babylon.permissions.repository.MemberRepository;
import com.babylon.permissions.sysytem.NowProvider;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.common.io.Resources.getResource;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PermissionsApplication.class})
public class MemberRepositoryTest {

  @Autowired
  private NowProvider nowProvider;

  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void findMember() throws IOException {
    createMemberAndSave("policies/translation_manager_policy.json");
    Member member = memberRepository.findMember("en-GB");
    assertNotNull(member);
  }

  @Test
  public void findAllWithNotNullPolicy() throws IOException {
    createMemberAndSave("policies/reviewer_policy.json");
    List<Member> members = memberRepository.findAllWithNotNullPolicy(0);
    assertTrue(members.size() > 0);
  }

  @Test
  public void findByParam() throws IOException {
    Member member = createMemberAndSave("policies/reviewer_policy.json");
    Member byParam = memberRepository.findByParam(member.getId());
    System.out.println(byParam);
  }

  @Test
  public void save() throws IOException {
    Member save = createMemberAndSave("policies/reviewer_policy.json");
    assertNotNull(save.getId());
  }

  private Member createMemberAndSave(String fileName) throws IOException {
    String jsonPolicy = loadStringFromFile(fileName);

    Member member = Member.builder()
        .id(randomUUID())
        .externalId(randomUUID().toString())
        .encryptedEmail(randomUUID().toString().getBytes())
        .encryptedFullName(randomUUID().toString().getBytes())
        .policy(jsonPolicy)
        .createdAt(nowProvider.nowUtc())
        .updatedAt(nowProvider.nowUtc())
        .lastActiveAt(nowProvider.nowUtc())
        .build();

    return memberRepository.save(member);
  }

  private String loadStringFromFile(String fileName) throws IOException {
    return Resources.toString(getResource(fileName), Charset.defaultCharset());
  }
}
