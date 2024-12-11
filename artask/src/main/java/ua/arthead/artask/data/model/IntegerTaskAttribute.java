package ua.arthead.artask.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class IntegerTaskAttribute extends TaskAttribute<Integer> {
  private Integer attributeValue;

  public IntegerTaskAttribute(String attributeName, Integer attributeValue) {
    super(attributeName);
    this.attributeValue = attributeValue;
  }
}
