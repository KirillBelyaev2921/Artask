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
public class StringTaskAttribute extends TaskAttribute<String> {
  private String attributeValue;

  public StringTaskAttribute(String attributeName, String attributeValue) {
    super(attributeName);
    this.attributeValue = attributeValue;
  }
}
