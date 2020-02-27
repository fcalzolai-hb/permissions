package com.babylon.permissions.dao;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity(name = "Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)
public class Role {

  @Id
  private UUID id;
  private String name;

  @Type(type = "jsonb")
  @Column(columnDefinition = "jsonb")
  private String policy;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
