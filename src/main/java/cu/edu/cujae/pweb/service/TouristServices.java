package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.TouristDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TouristServices {

    private ArrayList<TouristDto> tou = initializerTou();

    public void delete(TouristDto tourist) {
        tou.remove(tourist);
    }

    public void create(Object dto) {
        TouristDto tourist = (TouristDto) dto;
        tou.add(tourist);
    }

    public void update(TouristDto tourist, int code) {
        for (TouristDto t : tou) {
            if (t.getCode() == code) {
                t.setName(tourist.getName());
                t.setLastName(tourist.getLastName());
                t.setIdPassport(tourist.getIdPassport());
                t.setAge(tourist.getAge());
                t.setSex(tourist.getSex());
                t.setTelephoneNumber(tourist.getTelephoneNumber());
                t.setCountry(tourist.getCountry());
            }
        }
    }

    public boolean existID(int code) {
        boolean exist = false;
        for (int i = 0; i < tou.size(); i++) {
            if (tou.get(i).getCode() == code) {
                exist = true;
                i = tou.size();
            }
        }
        return exist;
    }


    public ArrayList<TouristDto> initializerTou() {
        ArrayList<TouristDto> tourists = new ArrayList<>();
        tourists.add(new TouristDto(
                0,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));
        tourists.add(new TouristDto(
                1,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));
        tourists.add(new TouristDto(
                2,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));
        tourists.add(new TouristDto(
                3,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));
        tourists.add(new TouristDto(
                4,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));
        tourists.add(new TouristDto(
                5,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));
        tourists.add(new TouristDto(
                6,
                "Erne",
                "Abella",
                "G456778",
                22,
                "Masculino",
                "7889654",
                "Spain"
        ));

        return tourists;
    }

    public ArrayList<TouristDto> getAll() {
        return tou;
    }

}
