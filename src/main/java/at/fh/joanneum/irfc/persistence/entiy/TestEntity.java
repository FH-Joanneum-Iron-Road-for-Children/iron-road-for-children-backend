package at.fh.joanneum.irfc.persistence.entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author moe@softwaregaertner.at
 **/
@Entity
@Table(name = "test")
@NoArgsConstructor
@Getter
@Setter
public class TestEntity {

  @Id
  @SequenceGenerator(
      name = "test_id_seq",
      sequenceName = "test_id_seq",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "test_id_seq"
  )
  @Column(name = "test_id")
  private Long id;

  @Column
  @NotEmpty
  public String name;

  @Column
  public int number;

}
