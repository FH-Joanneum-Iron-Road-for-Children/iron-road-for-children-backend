package at.fh.joanneum.irfc.service;

import at.fh.joanneum.irfc.model.TestDTO;
import at.fh.joanneum.irfc.persistence.repository.TestRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * @author moe@softwaregaertner.at
 **/
@RequestScoped
public class TestService {

  @Inject
  TestRepository testRepository;

  public TestDTO testMethod(TestDTO testDTO) {
    TestDTO newDto = new TestDTO();
    newDto = testDTO;
    if(testDTO.getNumber() > 100 || testDTO.getNumber() < 0){
      throw new RuntimeException("wrong number");
    }



    return newDto;
  }


}
