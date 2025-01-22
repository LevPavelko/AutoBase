package AutoBase.service.repair_order_service;

import AutoBase.dto.RepairOrderDTO;

import java.util.List;
import java.util.Optional;

public interface RepairOrderService {
    void save(RepairOrderDTO repairOrderDTO);
    void update(RepairOrderDTO repairOrderDTO);
    List<RepairOrderDTO> findAll();
    Optional<RepairOrderDTO> findById(Long id);
}
