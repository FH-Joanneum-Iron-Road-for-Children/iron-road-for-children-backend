package at.fh.joanneum.irfc.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author moe@softwaregaertner.at
 **/
@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class TestDTO {
  @NonNull
  String name;
  int number;

}
