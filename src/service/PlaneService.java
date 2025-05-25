package service;

import dataTransfer.PlaneDTO;
import model.Plane;
import response.Response;
import java.util.ArrayList;
import loader.DataLoader;

public class PlaneService {

    private final ArrayList<Plane> planes = new ArrayList<>();

    public PlaneService(ArrayList<Plane> planesJson) {
        for(Plane p: planesJson){
            planes.add(p);
        }
    }
    
    ArrayList<Plane> getPlanes(){
        return planes;
    }

    // Crear un nuevo avión a partir de un DTO
    public Response createPlane(PlaneDTO dto) {
        for (Plane p : planes) {
            if (p.getId().equals(dto.getId())) {
                return new Response(false, "Plane with this ID already exists.");
            }
        }

        Plane plane = new Plane(
            dto.getId(),
            dto.getBrand(),
            dto.getModel(),
            dto.getMaxCapacity(),
            dto.getAirline()
        );

        planes.add(plane);
        return new Response(true, "Plane created successfully.");
    }

    // Obtener todos los aviones como DTOs
    public ArrayList<PlaneDTO> getAllPlanes() {
        ArrayList<PlaneDTO> dtos = new ArrayList<>();
        for (Plane p : planes) {
            dtos.add(toDTO(p));
        }
        return dtos;
    }

    // Buscar un avión por ID
    public PlaneDTO getPlane(String id) {
        for (Plane p : planes) {
            if (p.getId().equals(id)) {
                return this.toDTO(p);
            }
        }
        return null;
    }
    Plane getPlaneById(String id) {
        for (Plane p : planes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // Actualizar un avión existente usando un DTO
    public boolean updatePlane(PlaneDTO dto) {
        for (Plane p : planes) {
            if (p.getId().equals(dto.getId())) {
                planes.remove(p);
                Plane updated = new Plane(
                    dto.getId(),
                    dto.getBrand(),
                    dto.getModel(),
                    dto.getMaxCapacity(),
                    dto.getAirline()
                );
                planes.add(updated);
                return true;
            }
        }
        return false;
    }

    // Eliminar un avión
    public boolean deletePlane(String id) {
        return planes.removeIf(p -> p.getId().equals(id));
    }

    // Conversor de modelo a DTO
    private PlaneDTO toDTO(Plane p) {
        return new PlaneDTO(
            p.getId(),
            p.getBrand(),
            p.getModel(),
            p.getMaxCapacity(),
            p.getAirline()
        );
    }
    
    public int getNumFlights(PlaneDTO planeDTO){
        for(Plane plane: planes){
            if(plane.getId()==planeDTO.getId()){
                return plane.getNumFlights();
            }
        }
        return 0;
    }
}


