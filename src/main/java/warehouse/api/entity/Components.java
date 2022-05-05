package warehouse.api.entity;

import java.util.ArrayList;
import java.util.List;

// Class to store the list of
// all the employees in an
// Array List
public class Components{

    private List<Component> componentList;

    // Method to return the list
    // of employees
    public List<Component> getComponentList()
    {

        if (componentList == null) {

            componentList
                    = new ArrayList<>();


        }

        return componentList;


    }

    public void
    setComponentList(
            List<Component> componentList)
    {
        this.componentList
                = componentList;
    }
}