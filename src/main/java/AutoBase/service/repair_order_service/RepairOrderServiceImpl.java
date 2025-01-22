package AutoBase.service.repair_order_service;

import AutoBase.convert.Converter;
import AutoBase.dao.RepairOrderRepository;
import AutoBase.dto.OrderDTO;
import AutoBase.dto.RepairOrderDTO;
import AutoBase.model.Order;
import AutoBase.model.RepairOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepairOrderServiceImpl implements RepairOrderService {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private Converter converter;

    @Override
    public void save(RepairOrderDTO repairOrderDTO) {
        RepairOrder repairOrder = converter.convertToEntity(repairOrderDTO);
        repairOrderRepository.save(repairOrder);
    }

    @Override
    public void update(RepairOrderDTO repairOrderDTO) {
        RepairOrder repairOrder = converter.convertToEntity(repairOrderDTO);
        repairOrderRepository.save(repairOrder);
    }

    @Override
    public List<RepairOrderDTO> findAll() {
        List<RepairOrder> repairOrders = repairOrderRepository.findAll();
        return repairOrders.stream()
                .map(converter::convertRepairOrderToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RepairOrderDTO> findById(Long id) {
        Optional<RepairOrder> repairOrder = repairOrderRepository.findById(id);
        return repairOrder.map(converter::convertRepairOrderToDTO);
    }
}
