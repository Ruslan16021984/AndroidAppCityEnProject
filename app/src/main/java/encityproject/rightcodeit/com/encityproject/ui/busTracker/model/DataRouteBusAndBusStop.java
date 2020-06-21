package encityproject.rightcodeit.com.encityproject.ui.busTracker.model;

import android.graphics.Color;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class DataRouteBusAndBusStop {
    public static Polyline line;

    // автобус номер 2 , остановки , маршрут
    public ArrayList<BusStop> BusStop_2() {
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
        arrayListRouteBusTwo.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        arrayListRouteBusTwo.add(new GeoPoint(47.486703, 34.656126)); // поворот возле дома Престиж
        arrayListRouteBusTwo.add(new GeoPoint(47.484222, 34.655152)); // поворот от котеджей на козацкую
        arrayListRouteBusTwo.add(new GeoPoint(47.482946, 34.661864)); // поворот возле чайки
        arrayListRouteBusTwo.add(new GeoPoint(47.487103, 34.663542)); // поворот в молитвенный дом
        arrayListRouteBusTwo.add(new GeoPoint(47.490695, 34.660603)); // поворот от  7 остановки на мерию
        arrayListRouteBusTwo.add(new GeoPoint(47.493598, 34.668175)); // набережная
        arrayListRouteBusTwo.add(new GeoPoint(47.491395, 34.662459)); // поворот от мерии на таврию
        arrayListRouteBusTwo.add(new GeoPoint(47.499788, 34.655494)); // от совеременника по алее роз
        arrayListRouteBusTwo.add(new GeoPoint(47.497171, 34.648595)); // от алеи роз на кольцо
        arrayListRouteBusTwo.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        line = new Polyline();
        line.setPoints(arrayListRouteBusTwo);
        line.setColor(Color.GRAY);
        map.getOverlayManager().add(line);
        map.invalidate();
    }


    // автобус номер 3 , остановки , маршрут
    public ArrayList<BusStop> BusStop_3() {
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
        arrayListRouteBusThree.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        arrayListRouteBusThree.add(new GeoPoint(47.486703, 34.656126)); // поворот возле дома Престиж
        arrayListRouteBusThree.add(new GeoPoint(47.484222, 34.655152)); // поворот от котеджей на козацкую
        arrayListRouteBusThree.add(new GeoPoint(47.482946, 34.661864)); // поворот возле чайки
        arrayListRouteBusThree.add(new GeoPoint(47.487103, 34.663542)); // поворот в молитвенный дом
        arrayListRouteBusThree.add(new GeoPoint(47.490695, 34.660603)); // поворот от  7 остановки на мерию
        arrayListRouteBusThree.add(new GeoPoint(47.491395, 34.662459)); // поворот от мерии на таврию
        arrayListRouteBusThree.add(new GeoPoint(47.501790, 34.653691)); // поворот перед военкоматом
        arrayListRouteBusThree.add(new GeoPoint(47.503377, 34.647468)); // поворот возле калибри
        arrayListRouteBusThree.add(new GeoPoint(47.500478, 34.645848)); // поворот перед церквой
        arrayListRouteBusThree.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        line = new Polyline();
        line.setPoints(arrayListRouteBusThree);
        line.setColor(Color.GREEN);
        map.getOverlayManager().add(line);
        map.invalidate();
    }


    // автобус номер 4 , остановки , маршрут
    public ArrayList<BusStop> bustStop_4() {
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
        arrayListRouteBusFour.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        arrayListRouteBusFour.add(new GeoPoint(47.486703, 34.656126)); // поворот возле дома Престиж
        arrayListRouteBusFour.add(new GeoPoint(47.484222, 34.655152)); // поворот от котеджей на козацкую
        arrayListRouteBusFour.add(new GeoPoint(47.482946, 34.661864)); // поворот возле чайки\
        arrayListRouteBusFour.add(new GeoPoint(47.487103, 34.663542)); // поворот в молитвенный дом
        arrayListRouteBusFour.add(new GeoPoint(47.490695, 34.660603)); // поворот от  7 остановки на мерию
        arrayListRouteBusFour.add(new GeoPoint(47.491395, 34.662459)); // поворот от мерии на таврию
        arrayListRouteBusFour.add(new GeoPoint(47.499788, 34.655494)); // от совеременника по алее роз
        arrayListRouteBusFour.add(new GeoPoint(47.497171, 34.648595)); // от алеи роз на кольцо
        arrayListRouteBusFour.add(new GeoPoint(47.4957672, 34.6428599)); // поворот на старую дорогу
        arrayListRouteBusFour.add(new GeoPoint(47.508756, 34.593143)); // поворот на вторую проходную
        arrayListRouteBusFour.add(new GeoPoint(47.513633, 34.595589)); // вторая проходная точка 1
        arrayListRouteBusFour.add(new GeoPoint(47.513945, 34.594291)); // вторая проходная точка 2
        arrayListRouteBusFour.add(new GeoPoint(47.512677, 34.595171)); // вторая проходная точка 3 // поворот на 1 ю  проходную со второй
        arrayListRouteBusFour.add(new GeoPoint(47.505814, 34.591384)); // поворот на первую проходную точка 1
        arrayListRouteBusFour.add(new GeoPoint(47.505531, 34.590429)); // поворот на первую проходную точка 2
        arrayListRouteBusFour.add(new GeoPoint(47.504981, 34.586116)); // поворот на первую проходную точка 3
        arrayListRouteBusFour.add(new GeoPoint(47.506387, 34.587039)); // поворот на первую проходную точка 4
        arrayListRouteBusFour.add(new GeoPoint(47.505329, 34.591277)); // поворот на первую проходную точка 5
        arrayListRouteBusFour.add(new GeoPoint(47.497262, 34.623233)); // поворот на старую дорогу с новой
        arrayListRouteBusFour.add(new GeoPoint(47.500553, 34.624971)); // поворот с междудорожья на старую дорогу
        arrayListRouteBusFour.add(new GeoPoint(47.4957672, 34.6428599)); // поворот на кольцо
        arrayListRouteBusFour.add(new GeoPoint(47.497171, 34.648595)); // от алеи роз на кольцо
        arrayListRouteBusFour.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        line = new Polyline();
        line.setPoints(arrayListRouteBusFour);
        line.setColor(Color.BLUE);
        map.getOverlayManager().add(line);
        map.invalidate();
    }


    // автобус номер 5 , остановки , маршрут
    public ArrayList<BusStop> BusStop_5() {
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
        arrayListRouteBusFive.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        arrayListRouteBusFive.add(new GeoPoint(47.486703, 34.656126)); // поворот возле дома Престиж
        arrayListRouteBusFive.add(new GeoPoint(47.484222, 34.655152)); // поворот от котеджей на козацкую
        arrayListRouteBusFive.add(new GeoPoint(47.482946, 34.661864)); // поворот возле чайки
        arrayListRouteBusFive.add(new GeoPoint(47.487103, 34.663542)); // поворот в молитвенный дом
        arrayListRouteBusFive.add(new GeoPoint(47.490695, 34.660603)); // поворот от  7 остановки на мерию
        arrayListRouteBusFive.add(new GeoPoint(47.491395, 34.662459)); // поворот от мерии на таврию
        arrayListRouteBusFive.add(new GeoPoint(47.500756, 34.654660)); // поворот возле АТБ
        arrayListRouteBusFive.add(new GeoPoint(47.501416, 34.656468)); // поворот возле стадиона
        arrayListRouteBusFive.add(new GeoPoint(47.501782, 34.656186)); // второй поворот возле стадиона
        arrayListRouteBusFive.add(new GeoPoint(47.503819, 34.655418)); // поворот на приднестровскую
        arrayListRouteBusFive.add(new GeoPoint(47.505482, 34.647567)); // поворот возле оптовика
        arrayListRouteBusFive.add(new GeoPoint(47.500478, 34.645848)); // поворот перед церквой
        arrayListRouteBusFive.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        line = new Polyline();
        line.setPoints(arrayListRouteBusFive);
        line.setColor(Color.YELLOW);
        map.getOverlayManager().add(line);
        map.invalidate();
    }

    // автобус номер 7 , остановки , маршрут
    public ArrayList<BusStop> BusStop_7() {
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

        arrayListRouteBusSeven.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        arrayListRouteBusSeven.add(new GeoPoint(47.486703, 34.656126)); // поворот возле дома Престиж
        arrayListRouteBusSeven.add(new GeoPoint(47.479914, 34.653375)); // кладбище
        arrayListRouteBusSeven.add(new GeoPoint(47.484222, 34.655152)); // поворот от котеджей на козацкую
        arrayListRouteBusSeven.add(new GeoPoint(47.482946, 34.661864)); // поворот возле чайки
        arrayListRouteBusSeven.add(new GeoPoint(47.487103, 34.663542)); // поворот в молитвенный дом
        arrayListRouteBusSeven.add(new GeoPoint(47.490695, 34.660603)); // поворот от  7 остановки на мерию
        arrayListRouteBusSeven.add(new GeoPoint(47.491395, 34.662459)); // поворот от мерии на таврию
        arrayListRouteBusSeven.add(new GeoPoint(47.501790, 34.653691)); // поворот перед военкоматом
        arrayListRouteBusSeven.add(new GeoPoint(47.503377, 34.647468)); // поворот возле калибри
        arrayListRouteBusSeven.add(new GeoPoint(47.500478, 34.645848)); // поворот перед церквой
        arrayListRouteBusSeven.add(new GeoPoint(47.496961, 34.648737)); // кольцо
        line = new Polyline();
        line.setPoints(arrayListRouteBusSeven);
        line.setColor(Color.RED);
        map.getOverlayManager().add(line);
        map.invalidate();
    }

    public void clearPoliline() {
        if (line != null) {
        }
    }


}
