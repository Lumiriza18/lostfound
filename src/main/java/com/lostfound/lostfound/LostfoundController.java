
package com.lostfound.lostfound;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LostfoundController {

    private final BaranghilangRepository repository;

    public LostfoundController(BaranghilangRepository repository) {
        this.repository = repository;
    }

    // Menghitung statistik dashboard
    private void tambahStatistik(Model model) {
        List<BarangHilang> semuaBarang = repository.findAll();

        long totalBarang = semuaBarang.size();

        long jumlahHilang = semuaBarang.stream()
                .filter(b -> "HILANG".equalsIgnoreCase(b.getStatus()))
                .count();

        long jumlahDitemukan = semuaBarang.stream()
                .filter(b -> "DITEMUKAN".equalsIgnoreCase(b.getStatus()))
                .count();

        long laporanHariIni = semuaBarang.stream()
                .filter(b -> LocalDate.now().equals(b.getTanggal()))
                .count();

        model.addAttribute("totalBarang", totalBarang);
        model.addAttribute("jumlahHilang", jumlahHilang);
        model.addAttribute("jumlahDitemukan", jumlahDitemukan);
        model.addAttribute("laporanHariIni", laporanHariIni);
    }

    // HALAMAN DASHBOARD
    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("barangBaru", new BarangHilang());
        tambahStatistik(model);

        model.addAttribute("modeEdit", false);

        return "barangHilang";
    }

    // MENYIMPAN BARANG BARU ATAU HASIL EDIT
    @PostMapping("/simpan")
    public String simpan(@ModelAttribute BarangHilang barang) {
        repository.save(barang);
        return "redirect:/";
    }

    // HALAMAN DATA BARANG
    @GetMapping("/data-barang")
    public String dataBarang(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "SEMUA") String status,
            Model model) {

        List<BarangHilang> daftarBarang = repository.findAll();

        if (!q.isBlank()) {
            daftarBarang = daftarBarang.stream()
                    .filter(b -> b.getNamaBarang() != null
                            && b.getNamaBarang().toLowerCase()
                            .contains(q.toLowerCase()))
                    .toList();
        }

        if (!"SEMUA".equalsIgnoreCase(status)) {
            daftarBarang = daftarBarang.stream()
                    .filter(b -> status.equalsIgnoreCase(b.getStatus()))
                    .toList();
        }

        model.addAttribute("listBarang", daftarBarang);
        model.addAttribute("keyword", q);
        model.addAttribute("filterStatus", status);

        tambahStatistik(model);

        return "dataBarang";
    }

    // MENGEDIT BARANG
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Optional<BarangHilang> data = repository.findById(id);

        if (data.isPresent()) {
            model.addAttribute("barangBaru", data.get());
            model.addAttribute("modeEdit", true);

            tambahStatistik(model);

            return "barangHilang";
        }

        return "redirect:/data-barang";
    }

    // MENGHAPUS BARANG
    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

        return "redirect:/data-barang";
    }
}
