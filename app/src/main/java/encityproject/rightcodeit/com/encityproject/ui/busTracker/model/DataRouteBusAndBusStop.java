package encityproject.rightcodeit.com.encityproject.ui.busTracker.model;

import android.graphics.Color;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class DataRouteBusAndBusStop {
    public static Polyline line = new Polyline();

    // автобус номер 2 , остановки , маршрут
    public static ArrayList<BusStop> BusStop_2() {
        ArrayList<BusStop> arrayListBusStopTwo = new ArrayList<>();
        arrayListBusStopTwo.add(new BusStop("Кільце", 47.496655, 34.648988));
        arrayListBusStopTwo.add(new BusStop("Ринок", 47.492641, 34.652010));
        arrayListBusStopTwo.add(new BusStop("Автовокзал", 47.489773, 34.6541106));
        arrayListBusStopTwo.add(new BusStop("Котеджі", 47.4855951, 34.6557318));
        arrayListBusStopTwo.add(new BusStop("Козацька", 47.4833923, 34.6596882));
        arrayListBusStopTwo.add(new BusStop("Кураж", 47.4858059, 34.6630889));
        arrayListBusStopTwo.add(new BusStop("АТС", 47.4901352, 34.6611564));
        arrayListBusStopTwo.add(new BusStop("Наберіжна", 47.493598, 34.668175));
        arrayListBusStopTwo.add(new BusStop("Таврья", 47.4934457, 34.6608294));
        arrayListBusStopTwo.add(new BusStop("Грязелікарня", 47.4963555, 34.6584116));
        arrayListBusStopTwo.add(new BusStop("Сучасник", 47.4996186, 34.6558457));
        arrayListBusStopTwo.add(new BusStop("Кільце", 47.496655, 34.648988));
        return arrayListBusStopTwo;
    }

    public static void routeBusTwo(MapView map) {
        ArrayList<GeoPoint> arrayListRouteBusTwo = new ArrayList<>();
        arrayListRouteBusTwo.add(new GeoPoint(47.4969853, 34.6487518)); // кольцо
        arrayListRouteBusTwo.add(new GeoPoint(47.4966122, 34.6490754));
        arrayListRouteBusTwo.add(new GeoPoint(47.4961897, 34.6494491));
        arrayListRouteBusTwo.add(new GeoPoint(47.4952282, 34.6502323));
        arrayListRouteBusTwo.add(new GeoPoint(47.4944661, 34.6508254));
        arrayListRouteBusTwo.add(new GeoPoint(47.4931535, 34.6517533));
        arrayListRouteBusTwo.add(new GeoPoint(47.4927003, 34.6520741));
        arrayListRouteBusTwo.add(new GeoPoint(47.4923632, 34.6523174));
        arrayListRouteBusTwo.add(new GeoPoint(47.4919404, 34.6526225));
        arrayListRouteBusTwo.add(new GeoPoint(47.4914197, 34.6529808));
        arrayListRouteBusTwo.add(new GeoPoint(47.4911103, 34.6531937));
        arrayListRouteBusTwo.add(new GeoPoint(47.4903465, 34.6537349));
        arrayListRouteBusTwo.add(new GeoPoint(47.4902560, 34.6537977));
        arrayListRouteBusTwo.add(new GeoPoint(47.4894239, 34.6543754));
        arrayListRouteBusTwo.add(new GeoPoint(47.4879145, 34.6554362));
        arrayListRouteBusTwo.add(new GeoPoint(47.4870590, 34.6560298));
        arrayListRouteBusTwo.add(new GeoPoint(47.4869282, 34.6560904));
        arrayListRouteBusTwo.add(new GeoPoint(47.4867772, 34.6561355));
        arrayListRouteBusTwo.add(new GeoPoint(47.4866108, 34.6561477));
        arrayListRouteBusTwo.add(new GeoPoint(47.4864366, 34.6560886));
        arrayListRouteBusTwo.add(new GeoPoint(47.4860870, 34.6559512));
        arrayListRouteBusTwo.add(new GeoPoint(47.4857498, 34.6558187));
        arrayListRouteBusTwo.add(new GeoPoint(47.4842709, 34.6551953)); // поворот на казацкую ->  до поворот на воинов
        arrayListRouteBusTwo.add(new GeoPoint(47.4840753, 34.6562033));
        arrayListRouteBusTwo.add(new GeoPoint(47.4838308, 34.6574630));
        arrayListRouteBusTwo.add(new GeoPoint(47.4835313, 34.6590064));
        arrayListRouteBusTwo.add(new GeoPoint(47.4829591, 34.6619004)); // поворот с казацкой(воинов) на воинов -> до 7 остановки
        arrayListRouteBusTwo.add(new GeoPoint(47.4870649, 34.6635953));
        arrayListRouteBusTwo.add(new GeoPoint(47.4907246, 34.6606445)); // поворот от курчатова -7я остановка --до оранжевого дома
        arrayListRouteBusTwo.add(new GeoPoint(47.4914427, 34.6625266)); // (пот\ворот оранжевій дом с курчатова на строителей до ДК)
        arrayListRouteBusTwo.add(new GeoPoint(47.4935981, 34.6682264));
        arrayListRouteBusTwo.add(new GeoPoint(47.4914427, 34.6625266)); // (пот\ворот оранжевій дом с курчатова на строителей до ДК)
        arrayListRouteBusTwo.add(new GeoPoint(47.4958527, 34.6588717));
        arrayListRouteBusTwo.add(new GeoPoint(47.4998712, 34.6555802)); // от ДК  до поворота по строителей
        arrayListRouteBusTwo.add(new GeoPoint(47.4972024, 34.6485456)); // перед кольцом
        //полу  кольцо
        arrayListRouteBusTwo.add(new GeoPoint(47.4971780, 34.6485065));
        arrayListRouteBusTwo.add(new GeoPoint(47.4971401, 34.6484742));
        arrayListRouteBusTwo.add(new GeoPoint(47.4970987, 34.6484624));
        arrayListRouteBusTwo.add(new GeoPoint(47.4970503, 34.6484740));
        arrayListRouteBusTwo.add(new GeoPoint(47.4970209, 34.6484966));
        arrayListRouteBusTwo.add(new GeoPoint(47.4969872, 34.6485467));
        arrayListRouteBusTwo.add(new GeoPoint(47.4969747, 34.6485806));
        arrayListRouteBusTwo.add(new GeoPoint(47.4969656, 34.6486310));
        arrayListRouteBusTwo.add(new GeoPoint(47.4969717, 34.6487188));
        arrayListRouteBusTwo.add(new GeoPoint(47.4969853, 34.6487518));
        if (line!=null){

            line.onDestroy();
        }
        line.setPoints(arrayListRouteBusTwo);
        line.setColor(Color.parseColor("#E60000"));
        map.getOverlayManager().add(line);
        map.invalidate();
    }


    // автобус номер 3 , остановки , маршрут
    public static ArrayList<BusStop> BusStop_3() {
        ArrayList<BusStop> arrayListBusStopThree = new ArrayList<>();
        arrayListBusStopThree.add(new BusStop("Кільце", 47.496655, 34.648988));
        arrayListBusStopThree.add(new BusStop("Ринок", 47.492641, 34.652010));
        arrayListBusStopThree.add(new BusStop("Автовокзал", 47.489773, 34.6541106));
        arrayListBusStopThree.add(new BusStop("Котеджі", 47.4855951, 34.6557318));
        arrayListBusStopThree.add(new BusStop("Козацька", 47.4833923, 34.6596882));
        arrayListBusStopThree.add(new BusStop("Кураж", 47.4858059, 34.6630889));
        arrayListBusStopThree.add(new BusStop("АТС", 47.4901352, 34.6611564));
        arrayListBusStopThree.add(new BusStop("Таврья", 47.4934457, 34.6608294));
        arrayListBusStopThree.add(new BusStop("Грязелікарня", 47.4963555, 34.6584116));
        arrayListBusStopThree.add(new BusStop("Сучасник", 47.4996186, 34.6558457));
        arrayListBusStopThree.add(new BusStop("Приват Банк", 47.502707, 34.6502982));
        arrayListBusStopThree.add(new BusStop("Бульвар Юності", 47.5019441, 34.6466607));
        arrayListBusStopThree.add(new BusStop("Кільце", 47.496655, 34.648988));
        return arrayListBusStopThree;
    }

    public static void routeBusThree(MapView map) {

        ArrayList<GeoPoint> arrayListRouteBusThree = new ArrayList<>();
        arrayListRouteBusThree.add(new GeoPoint(47.4969853, 34.6487518)); // кольцо
        arrayListRouteBusThree.add(new GeoPoint(47.4966122, 34.6490754));
        arrayListRouteBusThree.add(new GeoPoint(47.4961897, 34.6494491));
        arrayListRouteBusThree.add(new GeoPoint(47.4952282, 34.6502323));
        arrayListRouteBusThree.add(new GeoPoint(47.4944661, 34.6508254));
        arrayListRouteBusThree.add(new GeoPoint(47.4931535, 34.6517533));
        arrayListRouteBusThree.add(new GeoPoint(47.4927003, 34.6520741));
        arrayListRouteBusThree.add(new GeoPoint(47.4923632, 34.6523174));
        arrayListRouteBusThree.add(new GeoPoint(47.4919404, 34.6526225));
        arrayListRouteBusThree.add(new GeoPoint(47.4914197, 34.6529808));
        arrayListRouteBusThree.add(new GeoPoint(47.4911103, 34.6531937));
        arrayListRouteBusThree.add(new GeoPoint(47.4903465, 34.6537349));
        arrayListRouteBusThree.add(new GeoPoint(47.4902560, 34.6537977));
        arrayListRouteBusThree.add(new GeoPoint(47.4894239, 34.6543754));
        arrayListRouteBusThree.add(new GeoPoint(47.4879145, 34.6554362));
        arrayListRouteBusThree.add(new GeoPoint(47.4870590, 34.6560298));
        arrayListRouteBusThree.add(new GeoPoint(47.4869282, 34.6560904));
        arrayListRouteBusThree.add(new GeoPoint(47.4867772, 34.6561355));
        arrayListRouteBusThree.add(new GeoPoint(47.4866108, 34.6561477));
        arrayListRouteBusThree.add(new GeoPoint(47.4864366, 34.6560886));
        arrayListRouteBusThree.add(new GeoPoint(47.4860870, 34.6559512));
        arrayListRouteBusThree.add(new GeoPoint(47.4857498, 34.6558187));
        arrayListRouteBusThree.add(new GeoPoint(47.4842709, 34.6551953)); // поворот на казацкую ->  до поворот на воинов
        arrayListRouteBusThree.add(new GeoPoint(47.4840753, 34.6562033));
        arrayListRouteBusThree.add(new GeoPoint(47.4838308, 34.6574630));
        arrayListRouteBusThree.add(new GeoPoint(47.4835313, 34.6590064));
        arrayListRouteBusThree.add(new GeoPoint(47.4829591, 34.6619004)); // поворот с казацкой(воинов) на воинов -> до 7 остановки
        arrayListRouteBusThree.add(new GeoPoint(47.4870649, 34.6635953));
        arrayListRouteBusThree.add(new GeoPoint(47.4907246, 34.6606445)); // поворот от курчатова -7я остановка --до оранжевого дома
        arrayListRouteBusThree.add(new GeoPoint(47.4914427, 34.6625266)); // (пот\ворот оранжевій дом с курчатова на строителей до ДК)
        arrayListRouteBusThree.add(new GeoPoint(47.4958527, 34.6588717));
        arrayListRouteBusThree.add(new GeoPoint(47.4998712, 34.6555802)); // от ДК  до поворота по строителей
        arrayListRouteBusThree.add(new GeoPoint(47.5017396, 34.6539575));
        arrayListRouteBusThree.add(new GeoPoint(47.5022912, 34.6517210));
        arrayListRouteBusThree.add(new GeoPoint(47.5026987, 34.6502651));
        arrayListRouteBusThree.add(new GeoPoint(47.5029144, 34.6494401));
        arrayListRouteBusThree.add(new GeoPoint(47.5033924, 34.6475549)); // от  строителей по молодежной --чайка до кольца
        arrayListRouteBusThree.add(new GeoPoint(47.5005216, 34.6458908));
        arrayListRouteBusThree.add(new GeoPoint(47.4975425, 34.6483210));
        arrayListRouteBusThree.add(new GeoPoint(47.4972024, 34.6485456)); // перед кольцом
        //полу  кольцо
        arrayListRouteBusThree.add(new GeoPoint(47.4971780, 34.6485065));
        arrayListRouteBusThree.add(new GeoPoint(47.4971401, 34.6484742));
        arrayListRouteBusThree.add(new GeoPoint(47.4970987, 34.6484624));
        arrayListRouteBusThree.add(new GeoPoint(47.4970503, 34.6484740));
        arrayListRouteBusThree.add(new GeoPoint(47.4970209, 34.6484966));
        arrayListRouteBusThree.add(new GeoPoint(47.4969872, 34.6485467));
        arrayListRouteBusThree.add(new GeoPoint(47.4969747, 34.6485806));
        arrayListRouteBusThree.add(new GeoPoint(47.4969656, 34.6486310));
        arrayListRouteBusThree.add(new GeoPoint(47.4969717, 34.6487188));
        arrayListRouteBusThree.add(new GeoPoint(47.4969853, 34.6487518));
        if (line!=null){

            line.onDestroy();
        }
        line.setPoints(arrayListRouteBusThree);
        line.setColor(Color.parseColor("#09C000"));
        map.getOverlayManager().add(line);
        map.invalidate();
    }


    // автобус номер 4 , остановки , маршрут
    public static ArrayList<BusStop> BusStop_4() {
        ArrayList<BusStop> arrayListBusStopFour = new ArrayList<>();

        arrayListBusStopFour.add(new BusStop("Кільце", 47.496655, 34.648988));
        arrayListBusStopFour.add(new BusStop("Ринок", 47.492641, 34.652010));
        arrayListBusStopFour.add(new BusStop("Автовокзал", 47.489773, 34.6541106));
        arrayListBusStopFour.add(new BusStop("Котеджі", 47.4855951, 34.6557318));
        arrayListBusStopFour.add(new BusStop("Козацька", 47.4833923, 34.6596882));
        arrayListBusStopFour.add(new BusStop("Кураж", 47.4858059, 34.6630889));
        arrayListBusStopFour.add(new BusStop("АТС", 47.4901352, 34.6611564));
        arrayListBusStopFour.add(new BusStop("Таврья", 47.4934457, 34.6608294));
        arrayListBusStopFour.add(new BusStop("Грязелікарня", 47.4963555, 34.6584116));
        arrayListBusStopFour.add(new BusStop("Сучасник", 47.4996186, 34.6558457));
        arrayListBusStopFour.add(new BusStop("ЗСК", 47.5012543, 34.6226838));
        arrayListBusStopFour.add(new BusStop("УПТК", 47.5048706, 34.6083147));
        arrayListBusStopFour.add(new BusStop("Прохідная 2", 47.513723, 34.594073));
        arrayListBusStopFour.add(new BusStop("Прохідная 1", 47.506005, 34.585737));
        arrayListBusStopFour.add(new BusStop("Дачи", 47.4973616, 34.622959));
        arrayListBusStopFour.add(new BusStop("ЗСК", 47.5012543, 34.6226838));
        arrayListBusStopFour.add(new BusStop("Кільце", 47.496655, 34.648988));
        return arrayListBusStopFour;
    }

    public static void routeBusFour(MapView map) {

        ArrayList<GeoPoint> arrayListRouteBusFour = new ArrayList<>();
        arrayListRouteBusFour.add(new GeoPoint(47.4969853, 34.6487518)); // кольцо
        arrayListRouteBusFour.add(new GeoPoint(47.4966122, 34.6490754));
        arrayListRouteBusFour.add(new GeoPoint(47.4961897, 34.6494491));
        arrayListRouteBusFour.add(new GeoPoint(47.4952282, 34.6502323));
        arrayListRouteBusFour.add(new GeoPoint(47.4944661, 34.6508254));
        arrayListRouteBusFour.add(new GeoPoint(47.4931535, 34.6517533));
        arrayListRouteBusFour.add(new GeoPoint(47.4927003, 34.6520741));
        arrayListRouteBusFour.add(new GeoPoint(47.4923632, 34.6523174));
        arrayListRouteBusFour.add(new GeoPoint(47.4919404, 34.6526225));
        arrayListRouteBusFour.add(new GeoPoint(47.4914197, 34.6529808));
        arrayListRouteBusFour.add(new GeoPoint(47.4911103, 34.6531937));
        arrayListRouteBusFour.add(new GeoPoint(47.4903465, 34.6537349));
        arrayListRouteBusFour.add(new GeoPoint(47.4902560, 34.6537977));
        arrayListRouteBusFour.add(new GeoPoint(47.4894239, 34.6543754));
        arrayListRouteBusFour.add(new GeoPoint(47.4879145, 34.6554362));
        arrayListRouteBusFour.add(new GeoPoint(47.4870590, 34.6560298));
        arrayListRouteBusFour.add(new GeoPoint(47.4869282, 34.6560904));
        arrayListRouteBusFour.add(new GeoPoint(47.4867772, 34.6561355));
        arrayListRouteBusFour.add(new GeoPoint(47.4866108, 34.6561477));
        arrayListRouteBusFour.add(new GeoPoint(47.4864366, 34.6560886));
        arrayListRouteBusFour.add(new GeoPoint(47.4860870, 34.6559512));
        arrayListRouteBusFour.add(new GeoPoint(47.4857498, 34.6558187));
        arrayListRouteBusFour.add(new GeoPoint(47.4842709, 34.6551953)); // поворот на казацкую ->  до поворот на воинов
        arrayListRouteBusFour.add(new GeoPoint(47.4840753, 34.6562033));
        arrayListRouteBusFour.add(new GeoPoint(47.4838308, 34.6574630));
        arrayListRouteBusFour.add(new GeoPoint(47.4835313, 34.6590064));
        arrayListRouteBusFour.add(new GeoPoint(47.4829591, 34.6619004)); // поворот с казацкой(воинов) на воинов -> до 7 остановки
        arrayListRouteBusFour.add(new GeoPoint(47.4870649, 34.6635953));
        arrayListRouteBusFour.add(new GeoPoint(47.4907246, 34.6606445)); // поворот от курчатова -7я остановка --до оранжевого дома
        arrayListRouteBusFour.add(new GeoPoint(47.4914427, 34.6625266)); // (пот\ворот оранжевій дом с курчатова на строителей до ДК)
        arrayListRouteBusFour.add(new GeoPoint(47.4958527, 34.6588717));
        arrayListRouteBusFour.add(new GeoPoint(47.4998712, 34.6555802)); // от ДК  до поворота по строителей
        arrayListRouteBusFour.add(new GeoPoint(47.4972024, 34.6485456)); // перед кольцом
        arrayListRouteBusFour.add(new GeoPoint(47.4971780, 34.6485065));
        arrayListRouteBusFour.add(new GeoPoint(47.4971401, 34.6484742));
        arrayListRouteBusFour.add(new GeoPoint(47.4970987, 34.6484624));
        arrayListRouteBusFour.add(new GeoPoint(47.4970503, 34.6484740));// вїезд с кольца
        arrayListRouteBusFour.add(new GeoPoint(47.4967164, 34.6473146));
        arrayListRouteBusFour.add(new GeoPoint(47.4965479, 34.6465785));
        arrayListRouteBusFour.add(new GeoPoint(47.4962486, 34.6454716));
        arrayListRouteBusFour.add(new GeoPoint(47.4960402, 34.6444818));
        arrayListRouteBusFour.add(new GeoPoint(47.4959731, 34.6440366));
        arrayListRouteBusFour.add(new GeoPoint(47.4958991, 34.6438327));
        arrayListRouteBusFour.add(new GeoPoint(47.4959009, 34.6433459));
        arrayListRouteBusFour.add(new GeoPoint(47.4959566, 34.6428802));
        arrayListRouteBusFour.add(new GeoPoint(47.4995909, 34.6288431)); // примерній поворот... уточнить
        arrayListRouteBusFour.add(new GeoPoint(47.5087646, 34.5931836));
        arrayListRouteBusFour.add(new GeoPoint(47.5103700, 34.5940726));
        arrayListRouteBusFour.add(new GeoPoint(47.5120084, 34.5949509));
        arrayListRouteBusFour.add(new GeoPoint(47.5136047, 34.5955762));
        // тут  реализуем 2-ю  проходную
        arrayListRouteBusFour.add(new GeoPoint(47.51398, 34.59412));
        arrayListRouteBusFour.add(new GeoPoint(47.51316, 34.59364));
        arrayListRouteBusFour.add(new GeoPoint(47.51275, 34.59524139));
        arrayListRouteBusFour.add(new GeoPoint(47.5120084, 34.5949509));

        arrayListRouteBusFour.add(new GeoPoint(47.5120084, 34.5949509));
        arrayListRouteBusFour.add(new GeoPoint(47.5103700, 34.5940726));
        arrayListRouteBusFour.add(new GeoPoint(47.5087646, 34.5931836));
        arrayListRouteBusFour.add(new GeoPoint(47.5080660, 34.5927650));
        arrayListRouteBusFour.add(new GeoPoint(47.5060838, 34.5916405));
        arrayListRouteBusFour.add(new GeoPoint(47.5058516, 34.5914353));
        arrayListRouteBusFour.add(new GeoPoint(47.5056888, 34.5913726));
        arrayListRouteBusFour.add(new GeoPoint(47.5053636, 34.5912711)); // перекресток возле 1 - проходной
        // тут нужно организовать віїезд на 1 - ю проходную со всей хренью
        arrayListRouteBusFour.add(new GeoPoint(47.5057718, 34.5895895));
        arrayListRouteBusFour.add(new GeoPoint(47.5060055, 34.5887055));
        arrayListRouteBusFour.add(new GeoPoint(47.5064069, 34.5870795));
        arrayListRouteBusFour.add(new GeoPoint(47.5066480, 34.5861467));
        arrayListRouteBusFour.add(new GeoPoint(47.5050866, 34.5852657));
        arrayListRouteBusFour.add(new GeoPoint(47.5048463, 34.5861990));
        arrayListRouteBusFour.add(new GeoPoint(47.5064069, 34.5870795));
        arrayListRouteBusFour.add(new GeoPoint(47.5060055, 34.5887055));
        arrayListRouteBusFour.add(new GeoPoint(47.5057718, 34.5895895));

        arrayListRouteBusFour.add(new GeoPoint(47.5053636, 34.5912711));// перекресток возле 1 - проходной
        arrayListRouteBusFour.add(new GeoPoint(47.5052683, 34.5916806));
        arrayListRouteBusFour.add(new GeoPoint(47.5048138, 34.5940300));
        arrayListRouteBusFour.add(new GeoPoint(47.5046725, 34.5946220));
        // координаті поворота с новой на старую дорогу
        arrayListRouteBusFour.add(new GeoPoint(47.49730, 34.62324));
        arrayListRouteBusFour.add(new GeoPoint(47.5005745, 34.62502)); // примерній поворот... уточнить

        arrayListRouteBusFour.add(new GeoPoint(47.4959566, 34.6428802));
        arrayListRouteBusFour.add(new GeoPoint(47.4959009, 34.6433459));
        arrayListRouteBusFour.add(new GeoPoint(47.4958991, 34.6438327));
        arrayListRouteBusFour.add(new GeoPoint(47.4959731, 34.6440366));
        arrayListRouteBusFour.add(new GeoPoint(47.4960402, 34.6444818));
        arrayListRouteBusFour.add(new GeoPoint(47.4962486, 34.6454716));
        arrayListRouteBusFour.add(new GeoPoint(47.4964516, 34.6465864));
        arrayListRouteBusFour.add(new GeoPoint(47.4968180, 34.6477776));
        arrayListRouteBusFour.add(new GeoPoint(47.4970503, 34.6484740));// вїезд с кольца
        arrayListRouteBusFour.add(new GeoPoint(47.4970209, 34.6484966));
        arrayListRouteBusFour.add(new GeoPoint(47.4969872, 34.6485467));
        arrayListRouteBusFour.add(new GeoPoint(47.4969747, 34.6485806));
        arrayListRouteBusFour.add(new GeoPoint(47.4969656, 34.6486310));
        arrayListRouteBusFour.add(new GeoPoint(47.4969717, 34.6487188));
        arrayListRouteBusFour.add(new GeoPoint(47.4969853, 34.6487518));
        if (line!=null){

            line.onDestroy();
        }
        line.setPoints(arrayListRouteBusFour);
        line.setColor(Color.parseColor("#0040FF"));
        map.getOverlayManager().add(line);
        map.invalidate();
    }


    // автобус номер 5 , остановки , маршрут
    public static ArrayList<BusStop> BusStop_5() {
        ArrayList<BusStop> arrayListBusStopFive = new ArrayList<>();
        arrayListBusStopFive.add(new BusStop("Кільце", 47.496655, 34.648988));
        arrayListBusStopFive.add(new BusStop("Ринок", 47.492641, 34.652010));
        arrayListBusStopFive.add(new BusStop("Автовокзал", 47.489773, 34.6541106));
        arrayListBusStopFive.add(new BusStop("Котеджі", 47.4855951, 34.6557318));
        arrayListBusStopFive.add(new BusStop("Козацька", 47.4833923, 34.6596882));
        arrayListBusStopFive.add(new BusStop("Кураж", 47.4858059, 34.6630889));
        arrayListBusStopFive.add(new BusStop("АТС", 47.4901352, 34.6611564));
        arrayListBusStopFive.add(new BusStop("Таврья", 47.4934457, 34.6608294));
        arrayListBusStopFive.add(new BusStop("Грязелікарня", 47.4963555, 34.6584116));
        arrayListBusStopFive.add(new BusStop("Сучасник", 47.4996186, 34.6558457));
        arrayListBusStopFive.add(new BusStop("ДЮСША", 47.50427, 34.6536835));
        arrayListBusStopFive.add(new BusStop("Iнститут/Academy.RightCodeIt", 47.505271, 34.648523));
        arrayListBusStopFive.add(new BusStop("Кільце", 47.496655, 34.648988));
        return arrayListBusStopFive;
    }

    public static void routeBusFive(MapView map) {

        ArrayList<GeoPoint> arrayListRouteBusFive = new ArrayList<>();
        arrayListRouteBusFive.add(new GeoPoint(47.4969853, 34.6487518)); // кольцо
        arrayListRouteBusFive.add(new GeoPoint(47.4966122, 34.6490754));
        arrayListRouteBusFive.add(new GeoPoint(47.4961897, 34.6494491));
        arrayListRouteBusFive.add(new GeoPoint(47.4952282, 34.6502323));
        arrayListRouteBusFive.add(new GeoPoint(47.4944661, 34.6508254));
        arrayListRouteBusFive.add(new GeoPoint(47.4931535, 34.6517533));
        arrayListRouteBusFive.add(new GeoPoint(47.4927003, 34.6520741));
        arrayListRouteBusFive.add(new GeoPoint(47.4923632, 34.6523174));
        arrayListRouteBusFive.add(new GeoPoint(47.4919404, 34.6526225));
        arrayListRouteBusFive.add(new GeoPoint(47.4914197, 34.6529808));
        arrayListRouteBusFive.add(new GeoPoint(47.4911103, 34.6531937));
        arrayListRouteBusFive.add(new GeoPoint(47.4903465, 34.6537349));
        arrayListRouteBusFive.add(new GeoPoint(47.4902560, 34.6537977));
        arrayListRouteBusFive.add(new GeoPoint(47.4894239, 34.6543754));
        arrayListRouteBusFive.add(new GeoPoint(47.4879145, 34.6554362));
        arrayListRouteBusFive.add(new GeoPoint(47.4870590, 34.6560298));
        arrayListRouteBusFive.add(new GeoPoint(47.4869282, 34.6560904));
        arrayListRouteBusFive.add(new GeoPoint(47.4867772, 34.6561355));
        arrayListRouteBusFive.add(new GeoPoint(47.4866108, 34.6561477));
        arrayListRouteBusFive.add(new GeoPoint(47.4864366, 34.6560886));
        arrayListRouteBusFive.add(new GeoPoint(47.4860870, 34.6559512));
        arrayListRouteBusFive.add(new GeoPoint(47.4857498, 34.6558187));
        arrayListRouteBusFive.add(new GeoPoint(47.4842709, 34.6551953)); // поворот на казацкую ->  до поворот на воинов
        arrayListRouteBusFive.add(new GeoPoint(47.4840753, 34.6562033));
        arrayListRouteBusFive.add(new GeoPoint(47.4838308, 34.6574630));
        arrayListRouteBusFive.add(new GeoPoint(47.4835313, 34.6590064));
        arrayListRouteBusFive.add(new GeoPoint(47.4829591, 34.6619004)); // поворот с казацкой(воинов) на воинов -> до 7 остановки
        arrayListRouteBusFive.add(new GeoPoint(47.4870649, 34.6635953));
        arrayListRouteBusFive.add(new GeoPoint(47.4907246, 34.6606445)); // поворот от курчатова -7я остановка --до оранжевого дома
        arrayListRouteBusFive.add(new GeoPoint(47.4914427, 34.6625266)); // (пот\ворот оранжевій дом с курчатова на строителей до ДК)
        arrayListRouteBusFive.add(new GeoPoint(47.4958527, 34.6588717));
        arrayListRouteBusFive.add(new GeoPoint(47.4998712, 34.6555802)); // от ДК  до поворота по строителей
        arrayListRouteBusFive.add(new GeoPoint(47.5008220, 34.6547527));
        arrayListRouteBusFive.add(new GeoPoint(47.5014297, 34.6564566));
        arrayListRouteBusFive.add(new GeoPoint(47.5016024, 34.6563167));
        arrayListRouteBusFive.add(new GeoPoint(47.5016024, 34.6563167));
        arrayListRouteBusFive.add(new GeoPoint(47.5018821, 34.6562098));
        arrayListRouteBusFive.add(new GeoPoint(47.5028856, 34.6561934));
        arrayListRouteBusFive.add(new GeoPoint(47.5031241, 34.6560782));

        arrayListRouteBusFive.add(new GeoPoint(47.5038403, 34.6554968));
        arrayListRouteBusFive.add(new GeoPoint(47.5054427, 34.6491246));
        arrayListRouteBusFive.add(new GeoPoint(47.5055338, 34.6487128));
        arrayListRouteBusFive.add(new GeoPoint(47.5033924, 34.6475549)); // от  строителей по молодежной --чайка до кольца
        arrayListRouteBusFive.add(new GeoPoint(47.5005216, 34.6458908));
        arrayListRouteBusFive.add(new GeoPoint(47.4975425, 34.6483210));
        arrayListRouteBusFive.add(new GeoPoint(47.4972024, 34.6485456)); // перед кольцом
        //полу  кольцо
        arrayListRouteBusFive.add(new GeoPoint(47.4971780, 34.6485065));
        arrayListRouteBusFive.add(new GeoPoint(47.4971401, 34.6484742));
        arrayListRouteBusFive.add(new GeoPoint(47.4970987, 34.6484624));
        arrayListRouteBusFive.add(new GeoPoint(47.4970503, 34.6484740));
        arrayListRouteBusFive.add(new GeoPoint(47.4970209, 34.6484966));
        arrayListRouteBusFive.add(new GeoPoint(47.4969872, 34.6485467));
        arrayListRouteBusFive.add(new GeoPoint(47.4969747, 34.6485806));
        arrayListRouteBusFive.add(new GeoPoint(47.4969656, 34.6486310));
        arrayListRouteBusFive.add(new GeoPoint(47.4969717, 34.6487188));
        arrayListRouteBusFive.add(new GeoPoint(47.4969853, 34.6487518));
        if (line!=null){

            line.onDestroy();
        }
        line.setPoints(arrayListRouteBusFive);
        line.setColor(Color.parseColor("#FFDE00"));
        map.getOverlayManager().add(line);
        map.invalidate();
    }

    // автобус номер 7 , остановки , маршрут
    public static ArrayList<BusStop> BusStop_7() {
        ArrayList<BusStop> arrayListBusStopSeven = new ArrayList<>();
        arrayListBusStopSeven.add(new BusStop("Кільце", 47.496655, 34.648988));
        arrayListBusStopSeven.add(new BusStop("Ринок", 47.492641, 34.652010));
        arrayListBusStopSeven.add(new BusStop("Автовокзал", 47.489773, 34.6541106));
        arrayListBusStopSeven.add(new BusStop("Котеджі", 47.4855951, 34.6557318));
        arrayListBusStopSeven.add(new BusStop("Цвинтарь", 47.4798493, 34.6533506));
        arrayListBusStopSeven.add(new BusStop("РЕС", 47.4805836, 34.6536263));
        arrayListBusStopSeven.add(new BusStop("Козацька", 47.4833923, 34.6596882));
        arrayListBusStopSeven.add(new BusStop("Кураж", 47.4858059, 34.6630889));
        arrayListBusStopSeven.add(new BusStop("АТС", 47.4901352, 34.6611564));
        arrayListBusStopSeven.add(new BusStop("Таврья", 47.4934457, 34.6608294));
        arrayListBusStopSeven.add(new BusStop("Грязелікарня", 47.4963555, 34.6584116));
        arrayListBusStopSeven.add(new BusStop("Сучасник", 47.4996186, 34.6558457));
        arrayListBusStopSeven.add(new BusStop("Приват Банк", 47.502707, 34.6502982));
        arrayListBusStopSeven.add(new BusStop("Бульвар Юності", 47.5019441, 34.6466607));
        arrayListBusStopSeven.add(new BusStop("Кільце", 47.496655, 34.648988));
        return arrayListBusStopSeven;
    }

    public static void routeBusSeven(MapView map) {

        ArrayList<GeoPoint> arrayListRouteBusSeven = new ArrayList<>();


        // массив координат  от кольца-> до кладбища
        arrayListRouteBusSeven.add(new GeoPoint(47.4969853, 34.6487518)); // кольцо
        arrayListRouteBusSeven.add(new GeoPoint(47.4966122, 34.6490754));
        arrayListRouteBusSeven.add(new GeoPoint(47.4961897, 34.6494491));
        arrayListRouteBusSeven.add(new GeoPoint(47.4952282, 34.6502323));
        arrayListRouteBusSeven.add(new GeoPoint(47.4944661, 34.6508254));
        arrayListRouteBusSeven.add(new GeoPoint(47.4931535, 34.6517533));
        arrayListRouteBusSeven.add(new GeoPoint(47.4927003, 34.6520741));
        arrayListRouteBusSeven.add(new GeoPoint(47.4923632, 34.6523174));
        arrayListRouteBusSeven.add(new GeoPoint(47.4919404, 34.6526225));
        arrayListRouteBusSeven.add(new GeoPoint(47.4914197, 34.6529808));
        arrayListRouteBusSeven.add(new GeoPoint(47.4911103, 34.6531937));
        arrayListRouteBusSeven.add(new GeoPoint(47.4903465, 34.6537349));
        arrayListRouteBusSeven.add(new GeoPoint(47.4902560, 34.6537977));
        arrayListRouteBusSeven.add(new GeoPoint(47.4894239, 34.6543754));
        arrayListRouteBusSeven.add(new GeoPoint(47.4879145, 34.6554362));
        arrayListRouteBusSeven.add(new GeoPoint(47.4870590, 34.6560298));
        arrayListRouteBusSeven.add(new GeoPoint(47.4869282, 34.6560904));
        arrayListRouteBusSeven.add(new GeoPoint(47.4867772, 34.6561355));
        arrayListRouteBusSeven.add(new GeoPoint(47.4866108, 34.6561477));
        arrayListRouteBusSeven.add(new GeoPoint(47.4864366, 34.6560886));
        arrayListRouteBusSeven.add(new GeoPoint(47.4860870, 34.6559512));
        arrayListRouteBusSeven.add(new GeoPoint(47.4857498, 34.6558187));
        arrayListRouteBusSeven.add(new GeoPoint(47.4842709, 34.6551953)); // поворот на казацкую -> до кладбища
        arrayListRouteBusSeven.add(new GeoPoint(47.4834038, 34.6548576));
        arrayListRouteBusSeven.add(new GeoPoint(47.4824465, 34.6545054));
        arrayListRouteBusSeven.add(new GeoPoint(47.4816487, 34.6541912));
        arrayListRouteBusSeven.add(new GeoPoint(47.4815639, 34.6541559));
        arrayListRouteBusSeven.add(new GeoPoint(47.4801945, 34.6535558));

        arrayListRouteBusSeven.add(new GeoPoint(47.4798171, 34.6534125)); // кладбище -> до поворота на казацкую
        arrayListRouteBusSeven.add(new GeoPoint(47.4801945, 34.6535558));
        arrayListRouteBusSeven.add(new GeoPoint(47.4815639, 34.6541559));
        arrayListRouteBusSeven.add(new GeoPoint(47.4816487, 34.6541912));
        arrayListRouteBusSeven.add(new GeoPoint(47.4824465, 34.6545054));
        arrayListRouteBusSeven.add(new GeoPoint(47.4834038, 34.6548576));
        arrayListRouteBusSeven.add(new GeoPoint(47.4842709, 34.6551953)); // поворот на казацкую ->  до поворот на воинов
        arrayListRouteBusSeven.add(new GeoPoint(47.4840753, 34.6562033));
        arrayListRouteBusSeven.add(new GeoPoint(47.4838308, 34.6574630));
        arrayListRouteBusSeven.add(new GeoPoint(47.4835313, 34.6590064));
        arrayListRouteBusSeven.add(new GeoPoint(47.4829591, 34.6619004)); // поворот с казацкой(воинов) на воинов -> до 7 остановки
        arrayListRouteBusSeven.add(new GeoPoint(47.4870649, 34.6635953));
        arrayListRouteBusSeven.add(new GeoPoint(47.4907246, 34.6606445)); // поворот от курчатова -7я остановка --до оранжевого дома
        arrayListRouteBusSeven.add(new GeoPoint(47.4914427, 34.6625266)); // (пот\ворот оранжевій дом с курчатова на строителей до ДК)
        arrayListRouteBusSeven.add(new GeoPoint(47.4958527, 34.6588717));
        arrayListRouteBusSeven.add(new GeoPoint(47.4998712, 34.6555802)); // от ДК  до поворота по строителей
        arrayListRouteBusSeven.add(new GeoPoint(47.5017396, 34.6539575));
        arrayListRouteBusSeven.add(new GeoPoint(47.5022912, 34.6517210));
        arrayListRouteBusSeven.add(new GeoPoint(47.5026987, 34.6502651));
        arrayListRouteBusSeven.add(new GeoPoint(47.5029144, 34.6494401));
        arrayListRouteBusSeven.add(new GeoPoint(47.5033924, 34.6475549)); // от  строителей по молодежной --чайка до кольца
        arrayListRouteBusSeven.add(new GeoPoint(47.5005216, 34.6458908));
        arrayListRouteBusSeven.add(new GeoPoint(47.4975425, 34.6483210));
        arrayListRouteBusSeven.add(new GeoPoint(47.4972024, 34.6485456)); // перед кольцом
        //полу  кольцо
        arrayListRouteBusSeven.add(new GeoPoint(47.4971780, 34.6485065));
        arrayListRouteBusSeven.add(new GeoPoint(47.4971401, 34.6484742));
        arrayListRouteBusSeven.add(new GeoPoint(47.4970987, 34.6484624));
        arrayListRouteBusSeven.add(new GeoPoint(47.4970503, 34.6484740));
        arrayListRouteBusSeven.add(new GeoPoint(47.4970209, 34.6484966));
        arrayListRouteBusSeven.add(new GeoPoint(47.4969872, 34.6485467));
        arrayListRouteBusSeven.add(new GeoPoint(47.4969747, 34.6485806));
        arrayListRouteBusSeven.add(new GeoPoint(47.4969656, 34.6486310));
        arrayListRouteBusSeven.add(new GeoPoint(47.4969717, 34.6487188));
        arrayListRouteBusSeven.add(new GeoPoint(47.4969853, 34.6487518));

        if (line!=null){

            line.onDestroy();
        }
        line.setPoints(arrayListRouteBusSeven);
        line.setColor(Color.parseColor("#B700FF"));
        map.getOverlayManager().add(line);
        map.invalidate();
    }

    public void clearPoliline() {
        if (line != null) {
        }
    }


}


