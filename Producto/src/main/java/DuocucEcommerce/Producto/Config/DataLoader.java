package DuocucEcommerce.Producto.Config;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import DuocucEcommerce.Producto.Model.Almacenamiento;
import DuocucEcommerce.Producto.Model.CPU;
import DuocucEcommerce.Producto.Model.FuentePoder;
import DuocucEcommerce.Producto.Model.GPU;
import DuocucEcommerce.Producto.Model.Gabinete;
import DuocucEcommerce.Producto.Model.PlacaMadre;
import DuocucEcommerce.Producto.Model.Producto;
import DuocucEcommerce.Producto.Model.RAM;
import DuocucEcommerce.Producto.Repository.AlmacenamientoRepository;
import DuocucEcommerce.Producto.Repository.CPURepository;
import DuocucEcommerce.Producto.Repository.FuentePoderRepository;
import DuocucEcommerce.Producto.Repository.GPURepository;
import DuocucEcommerce.Producto.Repository.GabineteRepository;
import DuocucEcommerce.Producto.Repository.PlacaMadreRepository;
import DuocucEcommerce.Producto.Repository.ProductoRepository;
import DuocucEcommerce.Producto.Repository.RAMRepository;


@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDataBase(ProductoRepository productoRepository, CPURepository cpuRepository, GPURepository gpuRepository, RAMRepository ramRepository, PlacaMadreRepository placaMadreRepository, AlmacenamientoRepository almacenamientoRepository, FuentePoderRepository fuentePoderRepository, GabineteRepository gabineteRepository) {
        return args -> {
            if (productoRepository.count() == 0) {
                Producto cpu1 = productoRepository.save(Producto.builder().nombre("Ryzen 5 5600").marca("AMD").modelo("5600").precio(new BigDecimal("129990")).categoriaId(1).estado("ACTIVO").build());
                Producto cpu2 = productoRepository.save(Producto.builder().nombre("Intel i5 12400F").marca("Intel").modelo("12400F").precio(new BigDecimal("149990")).categoriaId(1).estado("ACTIVO").build());
                Producto gpu1 = productoRepository.save(Producto.builder().nombre("RTX 3060").marca("NVIDIA").modelo("RTX 3060 12GB").precio(new BigDecimal("329990")).categoriaId(2).estado("ACTIVO").build());
                Producto gpu2 = productoRepository.save(Producto.builder().nombre("RX 6600").marca("AMD").modelo("RX 6600 8GB").precio(new BigDecimal("249990")).categoriaId(2).estado("ACTIVO").build());
                Producto ram1 = productoRepository.save(Producto.builder().nombre("Kingston Fury 16GB DDR4").marca("Kingston").modelo("Fury Beast").precio(new BigDecimal("45990")).categoriaId(3).estado("ACTIVO").build());
                Producto ram2 = productoRepository.save(Producto.builder().nombre("Corsair Vengeance 16GB DDR5").marca("Corsair").modelo("Vengeance").precio(new BigDecimal("79990")).categoriaId(3).estado("ACTIVO").build());
                Producto mb1 = productoRepository.save(Producto.builder().nombre("Placa Madre B550").marca("ASUS").modelo("B550M-A").precio(new BigDecimal("109990")).categoriaId(4).estado("ACTIVO").build());
                Producto mb2 = productoRepository.save(Producto.builder().nombre("Placa Madre B660").marca("Gigabyte").modelo("B660M DS3H").precio(new BigDecimal("119990")).categoriaId(4).estado("ACTIVO").build());
                Producto ssd = productoRepository.save(Producto.builder().nombre("SSD Kingston NV2 1TB").marca("Kingston").modelo("NV2").precio(new BigDecimal("59990")).categoriaId(5).estado("ACTIVO").build());
                Producto hdd = productoRepository.save(Producto.builder().nombre("HDD Seagate 1TB").marca("Seagate").modelo("Barracuda").precio(new BigDecimal("39990")).categoriaId(5).estado("ACTIVO").build());
                Producto psu1 = productoRepository.save(Producto.builder().nombre("Fuente Corsair 650W").marca("Corsair").modelo("CV650").precio(new BigDecimal("69990")).categoriaId(6).estado("ACTIVO").build());
                Producto psu2 = productoRepository.save(Producto.builder().nombre("Fuente EVGA 600W").marca("EVGA").modelo("600W").precio(new BigDecimal("54990")).categoriaId(6).estado("ACTIVO").build());
                Producto case1 = productoRepository.save(Producto.builder().nombre("Gabinete ATX Basico").marca("Deepcool").modelo("Matrexx").precio(new BigDecimal("49990")).categoriaId(7).estado("ACTIVO").build());
                Producto case2 = productoRepository.save(Producto.builder().nombre("Gabinete Gamer ATX").marca("Corsair").modelo("4000D").precio(new BigDecimal("89990")).categoriaId(7).estado("ACTIVO").build());
                cpuRepository.save(CPU.builder().productoId(cpu1.getId()).socket("AM4").nucleos(6).hilos(12).tdpWatts(65).build());
                cpuRepository.save(CPU.builder().productoId(cpu2.getId()).socket("LGA1700").nucleos(6).hilos(12).tdpWatts(65).build());
                gpuRepository.save(GPU.builder().productoId(gpu1.getId()).memoriaGb(12).largoMm(242).tdpWatts(170).build());
                gpuRepository.save(GPU.builder().productoId(gpu2.getId()).memoriaGb(8).largoMm(190).tdpWatts(132).build());
                ramRepository.save(RAM.builder().productoId(ram1.getId()).tipoMemoria("DDR4").capacidadGb(16).frecuenciaMhz(3200).build());
                ramRepository.save(RAM.builder().productoId(ram2.getId()).tipoMemoria("DDR5").capacidadGb(16).frecuenciaMhz(5200).build());
                placaMadreRepository.save(PlacaMadre.builder().productoId(mb1.getId()).socket("AM4").chipset("B550").tipoRamSoportada("DDR4").ramMaximaGb(128).formato("Micro ATX").build());
                placaMadreRepository.save(PlacaMadre.builder().productoId(mb2.getId()).socket("LGA1700").chipset("B660").tipoRamSoportada("DDR4").ramMaximaGb(128).formato("Micro ATX").build());
                almacenamientoRepository.save(Almacenamiento.builder().productoId(ssd.getId()).tipo("SSD").capacidadGb(1000).interfaz("NVMe").build());
                almacenamientoRepository.save(Almacenamiento.builder().productoId(hdd.getId()).tipo("HDD").capacidadGb(1000).interfaz("SATA").build());
                fuentePoderRepository.save(FuentePoder.builder().productoId(psu1.getId()).potenciaWatts(650).certificacion("80 Plus Bronze").modular(false).build());
                fuentePoderRepository.save(FuentePoder.builder().productoId(psu2.getId()).potenciaWatts(600).certificacion("80 Plus White").modular(false).build());
                gabineteRepository.save(Gabinete.builder().productoId(case1.getId()).formatoSoportado("Micro ATX, ATX").largoMaxGpuMm(300).build());
                gabineteRepository.save(Gabinete.builder().productoId(case2.getId()).formatoSoportado("Micro ATX, ATX").largoMaxGpuMm(360).build());
            }
        };
    }
}

