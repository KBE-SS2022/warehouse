package warehouse.api.service;

import org.springframework.stereotype.Service;
import warehouse.api.entity.Component;
import warehouse.api.repository.ComponentDAO;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentDAO componentDAO = new ComponentDAO();

    public List<Component> getComponentsList(){
        return ComponentDAO.getComponentsList();
    }

    public List<Component> getComponent(Long id){
        return componentDAO.getComponent(id);
    }
}
