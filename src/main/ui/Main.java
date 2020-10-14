package ui;

import model.CollectionCentre;
import model.CollectionCentreHub;
import model.HealthAuthority;


public class Main {
    public static void main(String[] args) {
        new CollectionCentreLocatorApp();
    }

    public static void initializeCollectionCentres1(CollectionCentreHub hub) {
        CollectionCentre c1 = new CollectionCentre("Ridge Meadows", "153-11762 Laity Street",
                "Maple Ridge", null, HealthAuthority.FRASER, false, false,
                false, true, false);
        hub.addCollectionCentre(c1);
        CollectionCentre c2 = new CollectionCentre("Chilliwack COVID-19 Testing & Assessment Centre",
                "9090 Newman Road", "Chilliwack", "604-702-4928", HealthAuthority.FRASER,
                false, true, false, false, false);
        hub.addCollectionCentre(c2);
        CollectionCentre c3 = new CollectionCentre("Burnaby Central Park Drive-Thru Site",
                "Boundary Road and 46th Avenue", "Burnaby", null, HealthAuthority.FRASER,
                true, true, true, true, false);
        hub.addCollectionCentre(c3);
        CollectionCentre c4 = new CollectionCentre("Abbostsford COVID-19 Test Collection Centre",
                "1395 McKenzie Road", "Abbotsford", null, HealthAuthority.FRASER, false,
                true, false, true, false);
        hub.addCollectionCentre(c4);
        CollectionCentre c5 = new CollectionCentre("Mission Memorial Hospital Campus", "7324 Hurd Street",
                "Mission", null, HealthAuthority.FRASER, false, false,
                false, true, false);
        hub.addCollectionCentre(c5);
    }

    public static void initializeCollectionCentres2(CollectionCentreHub hub) {
        CollectionCentre c6 = new CollectionCentre("Peace Arch Hospital", "15521 Russell Avenue",
                "White Rock", "604-542-4057", HealthAuthority.FRASER, false, true,
                false, false, false);
        hub.addCollectionCentre(c6);
        CollectionCentre c7 = new CollectionCentre("South Delta", "4470 Clarence Taylor Crescent",
                "Delta", "604-952-3851", HealthAuthority.FRASER, false, true,
                false, true, false);
        hub.addCollectionCentre(c7);
        CollectionCentre c8 = new CollectionCentre("Surrey COVID-19 Test Collection Centre",
                "14577 6th Avenue", "Surrey", null, HealthAuthority.FRASER, false,
                true, false, true, false);
        hub.addCollectionCentre(c8);
        CollectionCentre c9 = new CollectionCentre("Langley COVID-19 Test Collection Centre",
                "20901 Langley Bypass", "Langley", null, HealthAuthority.FRASER, false,
                true, true, true, false);
        hub.addCollectionCentre(c9);
        CollectionCentre c10 = new CollectionCentre("Fraser Canyon Hospital", "1275 7th Avenue",
                "Hope", null, HealthAuthority.FRASER, false, false, false,
                true, false);
        hub.addCollectionCentre(c10);
    }

    public static void initializeCollectionCentres3(CollectionCentreHub hub) {
        CollectionCentre c11 = new CollectionCentre("Tri-Cities COVID-19 Test Collection Centre",
                "2796 Aberdeen Avenue", "Coquitlam", null, HealthAuthority.FRASER, false,
                true, false, false, false);
        hub.addCollectionCentre(c11);
        CollectionCentre c12 = new CollectionCentre("Vernon Urgent and Primary Care Centre",
                "3105 28th Avenue", "Vernon", "250-541-1097", HealthAuthority.INTERIOR,
                true, true, false, true, false);
        hub.addCollectionCentre(c12);
        CollectionCentre c13 = new CollectionCentre("Kewlona Urgent and Primary Care Centre",
                "1141 Harvey Street", "Kelowna", "250-469-6965", HealthAuthority.INTERIOR,
                true, true, false, true, false);
        hub.addCollectionCentre(c13);
        CollectionCentre c14 = new CollectionCentre("Penticton Health Unit", "550 Carmi Avenue",
                "Penticton", "250-770-3434", HealthAuthority.INTERIOR, true, true,
                false, true, false);
        hub.addCollectionCentre(c14);
        CollectionCentre c15 = new CollectionCentre("100 Mile House South Cariboo Health Centre",
                "555D Cedat Avenue", "100 Mile House", "250-395-7637", HealthAuthority.INTERIOR,
                true, true, false, true, false);
        hub.addCollectionCentre(c15);
    }


    public static void initializeCollectionCentres4(CollectionCentreHub hub) {

        CollectionCentre c16 = new CollectionCentre("Salmon Arm Public Health Centre",
                "851 16th Street N", "Salmon Arm", "250-833-4100", HealthAuthority.INTERIOR,
                true, true, false, true, false);
        hub.addCollectionCentre(c16);
        CollectionCentre c17 = new CollectionCentre("Cariboo Memorial Hospital", "525 Proctor Street",
                "Williams Lake,", "250-301-5006", HealthAuthority.INTERIOR, true, false,
                false, true, false);
        hub.addCollectionCentre(c17);
        CollectionCentre c18 = new CollectionCentre("Kootenay Lake Hospital", "3 View Street",
                "Nelson", "250-551-7500", HealthAuthority.INTERIOR, true, true,
                false, true, false);
        hub.addCollectionCentre(c18);
        CollectionCentre c19 = new CollectionCentre("Trail Kiro Wellness Centre", "1500 Columbia Avenue",
                "Trail", "250-304-5210", HealthAuthority.INTERIOR, true, true,
                false, true, false);
        hub.addCollectionCentre(c19);
        CollectionCentre c20 = new CollectionCentre("Cranbrook Health Unit Centre", "20-23rd Avenue S",
                "Cranbrook", "250-919-8406 or 250-417-9252", HealthAuthority.INTERIOR, true,
                true, false, true, false);
        hub.addCollectionCentre(c20);
    }

    public static void initializeCollectionCentres5(CollectionCentreHub hub) {

        CollectionCentre c21 = new CollectionCentre("Golden & District Hospital", "835 9th Avenue S",
                "Golden", "250-344-5271", HealthAuthority.INTERIOR, true, true,
                true, true, false);
        hub.addCollectionCentre(c21);
        CollectionCentre c22 = new CollectionCentre("Boundary District Hospital", "x7649 22nd Street",
                "Grand Forks", "250-443-2120", HealthAuthority.INTERIOR, true, true,
                false, true, false);
        hub.addCollectionCentre(c22);
        CollectionCentre c23 = new CollectionCentre("Revelstoke Health Centre", "1200 Newlands Road",
                "Revelstoke", "250-814-2230", HealthAuthority.INTERIOR, true, true,
                true, true, false);
        hub.addCollectionCentre(c23);
        CollectionCentre c24 = new CollectionCentre("Sparwood Health Centre", "570 Pine Avenue",
                "Sparwood", "250-425-3777", HealthAuthority.INTERIOR, true, true,
                true, true, false);
        hub.addCollectionCentre(c24);
        CollectionCentre c25 = new CollectionCentre("Lillooet Hospital Clinic", "951 Murray Street",
                "Lillooet", "250-256-1381", HealthAuthority.INTERIOR, true, false,
                false, true, false);
        hub.addCollectionCentre(c25);

    }

    public static void initializeCollectionCentres6(CollectionCentreHub hub) {

        CollectionCentre c26 = new CollectionCentre("Kamloops Public Health", "519 Columbia Street",
                "Kamloops", "250-851-7467", HealthAuthority.INTERIOR, true, true,
                false, true, false);
        hub.addCollectionCentre(c26);
        CollectionCentre c27 = new CollectionCentre("Nicola Valley Hospital and Health Centre",
                "3451 Voght Street", "Merrit", "250-378-3407", HealthAuthority.INTERIOR,
                true, false, false, true, false);
        hub.addCollectionCentre(c27);
        CollectionCentre c28 = new CollectionCentre("Creston Valley Hospital", "312 15th Avenue N",
                "Creston", "250-254-2055", HealthAuthority.INTERIOR, true, false,
                false, true, false);
        hub.addCollectionCentre(c28);
        CollectionCentre c29 = new CollectionCentre("Enderby Health Centre", "707 3rd Avenue",
                "Enderby", "250-838-2450", HealthAuthority.INTERIOR, true, false,
                false, true, false);
        hub.addCollectionCentre(c29);
        CollectionCentre c30 = new CollectionCentre("Salmo Wellness Centre", "413 Baker Avenue",
                "Salmo", "250-608-6174", HealthAuthority.INTERIOR, true, false,
                false, true, false);
        hub.addCollectionCentre(c30);

    }

    public static void initializeCollectionCentres7(CollectionCentreHub hub) {

        CollectionCentre c31 = new CollectionCentre("Invermere District Hospital", "850 10th Avenue",
                "Invermere", "250-341-5651", HealthAuthority.INTERIOR, true, true,
                false, true, false);
        hub.addCollectionCentre(c31);
        CollectionCentre c32 = new CollectionCentre("Castlegar COVID-19 Testing Site", "813 10th Street",
                "Castlegar", "250-608-5048", HealthAuthority.INTERIOR, true, true,
                true, true, false);
        hub.addCollectionCentre(c32);
        CollectionCentre c33 = new CollectionCentre("Ashcroft Health Centre",
                "700 Ash-Cache Creek Highway", "Ashcroft", "250-453-1905", HealthAuthority.INTERIOR,
                true, false, false, true, false);
        hub.addCollectionCentre(c33);
        CollectionCentre c34 = new CollectionCentre("Clearwater COVID-19 Collection Centre",
                "640 Park Drive", "Clearwater", "250-674-4107", HealthAuthority.INTERIOR,
                true, true, false, true, false);
        hub.addCollectionCentre(c34);
        CollectionCentre c35 = new CollectionCentre("Fort Nelson Health Unit", "5217 Airport Drive",
                "Fort Nelson", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c35);
    }

    public static void initializeCollectionCentres8(CollectionCentreHub hub) {

        CollectionCentre c36 = new CollectionCentre("Fort St. John Health Unit", "10115 110 Avenue",
                "Fort St.John", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c36);
        CollectionCentre c37 = new CollectionCentre("Hudson's Hope Health Centre", "10309 Hyllo Street",
                "Hudson's Hope", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c37);
        CollectionCentre c38 = new CollectionCentre("Dawson Creek Health Unit", "Rm157-1001 110 Avenue",
                "Dawson Creek", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c38);
        CollectionCentre c39 = new CollectionCentre("Tumbler Ridge Community Health Unit",
                "220 Front Street", "Tumbler Ridge", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, false, false, true, true);
        hub.addCollectionCentre(c39);
        CollectionCentre c40 = new CollectionCentre("Chetwynd Primary Care Clinic", "5125 50th Street SW",
                "Chetwynd", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c40);
    }


    public static void initializeCollectionCentres9(CollectionCentreHub hub) {
        CollectionCentre c41 = new CollectionCentre("Lakes District Health Center", "744 Center Street",
                "Burns Lake", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c41);
        CollectionCentre c42 = new CollectionCentre("Fraser Lake Diagnostic Health Center",
                "130 Chowsunket Road", "Fraser Lake", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, false, false, true, true);
        hub.addCollectionCentre(c42);
        CollectionCentre c43 = new CollectionCentre("St.John Hospital", "3255 Hospital Road",
                "Vanderhoof", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c43);
        CollectionCentre c44 = new CollectionCentre("Fort St.James Health Centre",
                "111-250 Douglas Avenue", "Fort St. James", "1-844-645-7811",
                HealthAuthority.NORTHERN, true, false, false, true,
                true);
        hub.addCollectionCentre(c44);
        CollectionCentre c45 = new CollectionCentre("Urgent Primary Care Centre", "543 Front Street",
                "Quesnel", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c45);
    }

    public static void initializeCollectionCentres10(CollectionCentreHub hub) {
        CollectionCentre c46 = new CollectionCentre("McBride Hospital", "1136 5th Avenue", "McBride",
                "1-844-645-7811", HealthAuthority.NORTHERN, true, true, false,
                true, true);
        hub.addCollectionCentre(c46);
        CollectionCentre c47 = new CollectionCentre("Valemount Health Center", "1145 5th Avenue",
                "Valemount", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c47);
        CollectionCentre c48 = new CollectionCentre("Mackenzie & District Hospital",
                "45 Cantennial Drive", "Mackenzie", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, false, false, true, true);
        hub.addCollectionCentre(c48);
        CollectionCentre c49 = new CollectionCentre("Prince George Urgent and Primary Care Centre",
                "1600 15th Avenue", "Prince George", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, true, false, true, true);
        hub.addCollectionCentre(c49);
        CollectionCentre c50 = new CollectionCentre("Haida Gwaii Hospital and Health Centre",
                "Room 3209 Oceanview Drive", "Queen Charlotte", "1-844-645-7811",
                HealthAuthority.NORTHERN, true, true, false, true,
                true);
        hub.addCollectionCentre(c50);
    }

    public static void initializeCollectionCentres11(CollectionCentreHub hub) {
        CollectionCentre c51 = new CollectionCentre("Northern Haida Gwaii Hospital and Health Centre",
                "2520 Harrison Avenue", "Village of Masset", "1-844-645-7811",
                HealthAuthority.NORTHERN, true, true, false, true,
                true);
        hub.addCollectionCentre(c51);
        CollectionCentre c52 = new CollectionCentre("Prince Rupert Regional Hospital",
                "1305 Summit Avenue", "Prince Rupert", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, false, false, true, true);
        hub.addCollectionCentre(c52);
        CollectionCentre c53 = new CollectionCentre("Kitimat General Hospital and Health Centre",
                "920 Lahakas Blvd. S", "Kitimat", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, true, false, true, true);
        hub.addCollectionCentre(c53);
        CollectionCentre c54 = new CollectionCentre("Mills Memorial Hospital", "4720 Haugland Avenue",
                "Terrace", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                true, true, true);
        hub.addCollectionCentre(c54);
        CollectionCentre c55 = new CollectionCentre("Stewart Health Centre - Emergency",
                "904 Brightwell Street", "Stewart", "1-844-645-7811", HealthAuthority.NORTHERN,
                true, false, false, true, true);
        hub.addCollectionCentre(c55);
    }

    public static void initializeCollectionCentres12(CollectionCentreHub hub) {
        CollectionCentre c56 = new CollectionCentre("Atlin Health Centre", "164 Third Street",
                "Atlin", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c56);
        CollectionCentre c57 = new CollectionCentre("Wrinch Memorial Hospital", "2510 Highway 62",
                "Hazelton", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c57);
        CollectionCentre c58 = new CollectionCentre("Stikine Health Centre", "7171 Highway 37",
                "Dease Lake", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c58);
        CollectionCentre c59 = new CollectionCentre("Bulkley Valley District Hospital", "3950 8th Avenue",
                "Smithers", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c59);
        CollectionCentre c60 = new CollectionCentre("BC Children & Women Campus", "4500 Oak Street",
                "Vancouver", "604-875-2154", HealthAuthority.PROVINCIAL, true, true,
                false, true, false);
        hub.addCollectionCentre(c60);
    }

    public static void initializeCollectionCentres13(CollectionCentreHub hub) {
        CollectionCentre c61 = new CollectionCentre("Squamish Assessment Centre", "40456 Government Road",
                "Squamish", "604-359-9559", HealthAuthority.COASTAL, false, true,
                true, true, false);
        hub.addCollectionCentre(c61);
        CollectionCentre c62 = new CollectionCentre("St. Vincent", "4875 Heather Street",
                "Vancouver", null, HealthAuthority.COASTAL, false, true,
                true, false, false);
        hub.addCollectionCentre(c62);
        CollectionCentre c63 = new CollectionCentre("City Centre UPCC", "1290 Hornby Street",
                "Vancouver", null, HealthAuthority.COASTAL, false, true,
                false, true, false);
        hub.addCollectionCentre(c63);
        CollectionCentre c64 = new CollectionCentre("North Vancouver UPCC",
                "Suite 200-221 West Esplanade", "North Vancouver", null, HealthAuthority.COASTAL,
                false, true, false, true, false);
        hub.addCollectionCentre(c64);
        CollectionCentre c65 = new CollectionCentre("REACH UPCC", "1145 Commercial Drive",
                "Vancouver", "604-216-3138", HealthAuthority.COASTAL, true, true,
                false, true, false);
        hub.addCollectionCentre(c65);
    }

    public static void initializeCollectionCentres14(CollectionCentreHub hub) {
        CollectionCentre c66 = new CollectionCentre("Sunshine Coast", "Provided after phoning",
                "Sechelt", "604-740-1252", HealthAuthority.COASTAL, true, true,
                false, true, false);
        hub.addCollectionCentre(c66);
        CollectionCentre c67 = new CollectionCentre("RW Large Memorial Hospital Emergency Department",
                "88 Waglisla Street", "Bella Bella", "250-957-2314", HealthAuthority.COASTAL,
                false, true, false, true, false);
        hub.addCollectionCentre(c67);
        CollectionCentre c68 = new CollectionCentre("Bella Coola General Hospital - Emergency Department",
                "1025 Elcho Street", "Bella Coola", "250-799-5311", HealthAuthority.COASTAL,
                false, true, false, true, false);
        hub.addCollectionCentre(c68);
        CollectionCentre c69 = new CollectionCentre("Powell River General Hospital - Emergency Department",
                "5000 Joyce Avenue", "Powell River", null, HealthAuthority.COASTAL,
                false, true, false, true, false);
        hub.addCollectionCentre(c69);
        CollectionCentre c70 = new CollectionCentre("Squamish General Hospital - Emergency Department",
                "38140 Behrner Drive", "Squamish", "604-892-5211", HealthAuthority.COASTAL,
                false, true, false, true, false);
        hub.addCollectionCentre(c70);
    }

    public static void initializeCollectionCentres15(CollectionCentreHub hub) {
        CollectionCentre c71 = new CollectionCentre("Whistler Medical Clinic", "4380 Lorimer Road",
                "Whistler", "604-210-5911", HealthAuthority.COASTAL, true, true,
                false, true, false);
        hub.addCollectionCentre(c71);
        CollectionCentre c72 = new CollectionCentre("Pemberton Health Centre",
                "1403 Pemberton Portafe Road", "Pemberton", "604-894-6633 or 604-894-6939",
                HealthAuthority.COASTAL, false, false, false, true,
                false);
        hub.addCollectionCentre(c72);
        CollectionCentre c73 = new CollectionCentre("DTES COVID-19 Testing Site", "429 Alexander Street",
                "Vancouver", "778-886-4081", HealthAuthority.COASTAL, false, false,
                false, false, false);
        hub.addCollectionCentre(c73);
        CollectionCentre c74 = new CollectionCentre("Richmond COVID Collection Centre",
                "6820 Gilbert Road", "Richmond", null, HealthAuthority.COASTAL, false,
                true, true, false, false);
        hub.addCollectionCentre(c74);
        CollectionCentre c75 = new CollectionCentre("Vancouver Community College - Parking Lot",
                "North Parking Lot #865 - Entrance between Keith and Glen Drive", "Vancouver", null,
                HealthAuthority.COASTAL, false, true, true, false,
                false);
        hub.addCollectionCentre(c75);
    }

    public static void initializeCollectionCentres16(CollectionCentreHub hub) {
        CollectionCentre c76 = new CollectionCentre("North Vancouver COVID-19 Assessment Centre",
                "255 Lloyd Avenue", "North Vancouver", null, HealthAuthority.COASTAL,
                false, true, true, false, false);
        hub.addCollectionCentre(c76);
        CollectionCentre c77 = new CollectionCentre("West Shore Collection and Assessnent Centre",
                "1767 Island Highway", "Victoria", "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        hub.addCollectionCentre(c77);
        CollectionCentre c78 = new CollectionCentre("Comox Valley Hospital", "101 Lerwick Road",
                "Courtenay", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c78);
        CollectionCentre c79 = new CollectionCentre("Campbell River Hospital", "375 2nd Avenune",
                "Campbell River", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c79);
        CollectionCentre c80 = new CollectionCentre("Port McNeill Hospital", "2750 Kingcome Place",
                "Port McNeill", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c80);
    }

    public static void initializeCollectionCentres17(CollectionCentreHub hub) {
        CollectionCentre c81 = new CollectionCentre("Port Hardy Hospital", "9120 Granville Street",
                "Port Hardy", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c81);
        CollectionCentre c82 = new CollectionCentre("West Coast General Hospital",
                "3949 Port Alberni Highway", "Port Alberni", "1-844-901-8442",
                HealthAuthority.ISLAND, true, true, false, true, false);
        hub.addCollectionCentre(c82);
        CollectionCentre c83 = new CollectionCentre("Public Health Building", "1665 Grant Avenue",
                "Nanaimo", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c83);
        CollectionCentre c84 = new CollectionCentre("Oceanside Health Centre", "489 Alberni Highway",
                "Parksville", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c84);
        CollectionCentre c85 = new CollectionCentre("Tofino General Hospital", "261 Neill Street",
                "Tofino", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c85);
    }

    public static void initializeCollectionCentres18(CollectionCentreHub hub) {
        CollectionCentre c86 = new CollectionCentre("Cowichan District Hospital", "3045 Gibbins Road",
                "Duncan", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c86);
        CollectionCentre c87 = new CollectionCentre("Peninsula Health Unit", "2170 Mt.Newton X Road",
                "Saanichton", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c87);
        CollectionCentre c88 = new CollectionCentre("Lady Minto Hospital", "135 Crofton Road",
                "Salt Spring Island", "1-844-901-8442", HealthAuthority.ISLAND, true,
                true, false, true, false);
        hub.addCollectionCentre(c88);
        CollectionCentre c89 = new CollectionCentre("Victoria Health Unit", "1947 Cook Street",
                "Victoria", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c89);
        CollectionCentre c90 = new CollectionCentre("Cormorant Island Health Centre", "49 School Road",
                "Alert Bay", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c90);
    }

    public static void initializeCollectionCentres19(CollectionCentreHub hub) {
        CollectionCentre c91 = new CollectionCentre("Sointula Health Centre", "25 2nd Street",
                "Sointula", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c91);
        CollectionCentre c92 = new CollectionCentre("Port Alice Health Centre", "1090 Marine Drive",
                "Port Alice", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c92);
        CollectionCentre c93 = new CollectionCentre("Gold River", "601 Trumpeter Drive",
                "Gold River", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                true, true, false);
        hub.addCollectionCentre(c93);
        CollectionCentre c94 = new CollectionCentre("Bamfield Health Centre", "353 Bamfield Road",
                "Bamfield", "1-844-901-8442", HealthAuthority.ISLAND, true, true,
                false, true, false);
        hub.addCollectionCentre(c94);
        CollectionCentre c95 = new CollectionCentre("Huu-ay-aht First Nations, a Nuu-chah-nulth Community",
                "415 Kiixen Road", "Huu ay aht", "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        hub.addCollectionCentre(c95);
    }

    public static void initializeCollectionCentres20(CollectionCentreHub hub) {
        CollectionCentre c96 = new CollectionCentre("Ditidaht First Nations, a Nuu-chah-nulth Community",
                "479 Malachan Indian Reserve Road", "Ditidaht", "1-844-901-8442",
                HealthAuthority.ISLAND, true, true, false, true, false);
        hub.addCollectionCentre(c96);
        CollectionCentre c97 = new CollectionCentre("Tseshaht First Nations, a Nuu-chah-nulth Community",
                "5001 Mission Road", "Tseshaht", "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        hub.addCollectionCentre(c97);
        CollectionCentre c98 = new CollectionCentre("Hupacasath First Nations, a Nuu-chah-nulth Community",
                "5500 Ahahswinis Drive", "Hupacasath", "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        hub.addCollectionCentre(c98);
        CollectionCentre c99 = new CollectionCentre("Houston Health Centre", "3202 14th Avenue",
                "Houston", "1-844-645-7811", HealthAuthority.NORTHERN, true, true,
                false, true, true);
        hub.addCollectionCentre(c99);
        CollectionCentre c100 = new CollectionCentre("Terrace Health Unit", "3412 Kalum Street",
                "Terrace", "1-844-645-7811", HealthAuthority.NORTHERN, true, false,
                false, true, true);
        hub.addCollectionCentre(c100);
    }

    public static void initializeAllCollectionCentres(CollectionCentreHub hub) {
        initializeCollectionCentres1(hub);
        initializeCollectionCentres2(hub);
        initializeCollectionCentres3(hub);
        initializeCollectionCentres4(hub);
        initializeCollectionCentres5(hub);
        initializeCollectionCentres6(hub);
        initializeCollectionCentres7(hub);
        initializeCollectionCentres8(hub);
        initializeCollectionCentres9(hub);
        initializeCollectionCentres10(hub);
        initializeCollectionCentres11(hub);
        initializeCollectionCentres12(hub);
        initializeCollectionCentres13(hub);
        initializeCollectionCentres14(hub);
        initializeCollectionCentres15(hub);
        initializeCollectionCentres16(hub);
        initializeCollectionCentres17(hub);
        initializeCollectionCentres18(hub);
        initializeCollectionCentres19(hub);
        initializeCollectionCentres20(hub);
    }

}
