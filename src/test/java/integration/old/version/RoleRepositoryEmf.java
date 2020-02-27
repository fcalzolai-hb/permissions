package integration.old.version;

import java.util.Arrays;
import java.util.List;

import com.babylon.permissions.PermissionsApplication;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {PermissionsApplication.class})
public class RoleRepositoryEmf {

  @PersistenceContext
  EntityManager entityManager;

  @Test
  public void queryAsString() {
    List resultList = entityManager.createNativeQuery(
        "SELECT r " +
            "FROM Role r ")
        .getResultList();
    System.out.println(resultList);
  }

  @Test
  public void queryAsObject() {
    List resultList = entityManager.createNativeQuery(
        "SELECT * " +
            "FROM Role ")
        .getResultList();
    System.out.println(resultList);
  }

  @Test
  public void testQuery() {
    List resultList = entityManager.createNativeQuery(
        "SELECT 1 ")
        .getResultList();
    System.out.println(resultList);
  }

  @Test
  public void testTable() {
    List resultList = entityManager.createNativeQuery(
        "SELECT * FROM test")
        .getResultList();
    resultList.forEach((o) -> System.out.println(Arrays.toString((Object[]) o)));
  }
}
