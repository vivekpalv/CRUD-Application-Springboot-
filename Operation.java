package RestAPI.Fetch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

//@Component
public interface Operation extends CrudRepository<Information,Integer> {
}
