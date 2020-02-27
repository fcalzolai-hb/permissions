package integration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.babylon.permissions.PermissionsApplication;
import com.babylon.permissions.dao.Role;
import com.babylon.permissions.repository.RoleRepository;
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
public class RoleRepositoryTest {

  @Autowired
  private NowProvider nowProvider;

  @Autowired
  private RoleRepository roleRepository;

  @Test
  public void findAllByProjectWithParam_0() {
    List<Role> roles = roleRepository.findAllByProject_1("ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllByProjectWithParam_1() {
    List<Role> roles = roleRepository.findAllByProject_1("ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllByProjectWithParam_2() {
    List<Role> roles = roleRepository.findAllByProject_2("ea8b601b-0ebc-4310-b512-ee7df8240e9f");
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllByProject() {
    List<Role> roles = roleRepository.findAllByProject();
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findAllWithNotNullPolicy() {
    List<Role> roles = roleRepository.findAllWithNotNullPolicy(0);
    assertTrue(roles.size() > 0);
  }

  @Test
  public void findByParam() throws IOException {
    Role role = createRoleAndSave("./policies/policy.json");
    Role byParam = roleRepository.findByParam(role.getId());
    assertNotNull(byParam);
  }

  @Test
  public void save() throws IOException {
    Role save = createRoleAndSave("./policies/policy.json");
    assertNotNull(save.getId());
  }

  private Role createRoleAndSave(String fileName) throws IOException {
    String jsonPolicy = loadStringFromFile(fileName);

    Role role = Role.builder()
        .id(randomUUID())
        .name(randomUUID().toString())
        .policy(jsonPolicy)
        .createdAt(nowProvider.nowUtc())
        .updatedAt(nowProvider.nowUtc())
        .build();

    return roleRepository.save(role);
  }

  private String loadStringFromFile(String fileName) throws IOException {
    return Resources.toString(getResource(fileName), Charset.defaultCharset());
  }
}
