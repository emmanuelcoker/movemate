package com.example.movemate.data

import com.example.movemate.R
import com.example.movemate.data.models.Shipment
import com.example.movemate.data.models.ShipmentStatus
import com.example.movemate.data.models.Vehicle
import com.example.movemate.data.models.VehicleType

val availableVehicles: List<Vehicle> = VehicleType.entries.map {
    Vehicle(
        title = it.type,
        subtitle = it.description,
        icon = it.icon
    )
}

val sampleShipments = listOf(
    Shipment(
        sentFrom = "Lagos, Nigeria",
        sentTo = "Hamburg, Germany",
        duration = "12 days",
        status = ShipmentStatus.InProgress,
        shipmentNumber = "SHIP-1001",
        vehicleType = VehicleType.SHIP,
        itemName = "Industrial Machines",
        amount = 1400.0
    ),
    Shipment(
        sentFrom = "New York, USA",
        sentTo = "London, UK",
        duration = "7 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "PLN-2045",
        vehicleType = VehicleType.PLANE,
        itemName = "Pharmaceutical Supplies",
        amount = 650.0
    ),
    Shipment(
        sentFrom = "Nairobi, Kenya",
        sentTo = "Kampala, Uganda",
        duration = "3 days",
        status = ShipmentStatus.Pending,
        shipmentNumber = "TRK-3098",
        vehicleType = VehicleType.TRUCK,
        itemName = "Agricultural Produce",
        amount = 650.0
    ),
    Shipment(
        sentFrom = "Shanghai, China",
        sentTo = "Los Angeles, USA",
        duration = "15 days",
        status = ShipmentStatus.Loading,
        shipmentNumber = "SHIP-1144",
        vehicleType = VehicleType.SHIP,
        itemName = "Consumer Electronics",
        amount = 650.0
    ),
    Shipment(
        sentFrom = "Paris, France",
        sentTo = "Rome, Italy",
        duration = "2 days",
        status = ShipmentStatus.Cancelled,
        shipmentNumber = "TRK-7711",
        vehicleType = VehicleType.TRUCK,
        itemName = "Fashion Apparel",
        amount = 650.0
    ),
    Shipment(
        sentFrom = "Tokyo, Japan",
        sentTo = "Sydney, Australia",
        duration = "10 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "PLN-5672",
        vehicleType = VehicleType.PLANE,
        itemName = "Automotive Parts",
        amount = 950.0
    ),
    Shipment(
        sentFrom = "Berlin, Germany",
        sentTo = "Warsaw, Poland",
        duration = "4 days",
        status = ShipmentStatus.InProgress,
        shipmentNumber = "TRK-2120",
        vehicleType = VehicleType.TRUCK,
        itemName = "Mechanical Equipment",
        amount = 500.0
    ),
    Shipment(
        sentFrom = "Cairo, Egypt",
        sentTo = "Lagos, Nigeria",
        duration = "6 days",
        status = ShipmentStatus.Pending,
        shipmentNumber = "SHIP-2011",
        vehicleType = VehicleType.SHIP,
        itemName = "Construction Materials",
        amount = 1200.0
    ),
    Shipment(
        sentFrom = "San Francisco, USA",
        sentTo = "Vancouver, Canada",
        duration = "5 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "PLN-3344",
        vehicleType = VehicleType.PLANE,
        itemName = "Medical Devices",
        amount = 870.0
    ),
    Shipment(
        sentFrom = "Mumbai, India",
        sentTo = "Dubai, UAE",
        duration = "8 days",
        status = ShipmentStatus.Loading,
        shipmentNumber = "SHIP-8866",
        vehicleType = VehicleType.SHIP,
        itemName = "Raw Textiles",
        amount = 540.0
    ),
    Shipment(
        sentFrom = "Accra, Ghana",
        sentTo = "Abidjan, Ivory Coast",
        duration = "3 days",
        status = ShipmentStatus.Cancelled,
        shipmentNumber = "TRK-1122",
        vehicleType = VehicleType.TRUCK,
        itemName = "Food Items",
        amount = 300.0
    ),
    Shipment(
        sentFrom = "Madrid, Spain",
        sentTo = "Lisbon, Portugal",
        duration = "2 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-2233",
        vehicleType = VehicleType.TRUCK,
        itemName = "Home Decor",
        amount = 400.0
    ),
    Shipment(
        sentFrom = "Beijing, China",
        sentTo = "Seoul, South Korea",
        duration = "4 days",
        status = ShipmentStatus.InProgress,
        shipmentNumber = "PLN-4411",
        vehicleType = VehicleType.PLANE,
        itemName = "Electronic Gadgets",
        amount = 760.0
    ),
    Shipment(
        sentFrom = "Buenos Aires, Argentina",
        sentTo = "Santiago, Chile",
        duration = "5 days",
        status = ShipmentStatus.Pending,
        shipmentNumber = "TRK-6677",
        vehicleType = VehicleType.TRUCK,
        itemName = "Canned Food",
        amount = 380.0
    ),
    Shipment(
        sentFrom = "Oslo, Norway",
        sentTo = "Copenhagen, Denmark",
        duration = "2 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-8899",
        vehicleType = VehicleType.TRUCK,
        itemName = "Kitchenware",
        amount = 310.0
    ),
    Shipment(
        sentFrom = "Jakarta, Indonesia",
        sentTo = "Bangkok, Thailand",
        duration = "6 days",
        status = ShipmentStatus.InProgress,
        shipmentNumber = "SHIP-2202",
        vehicleType = VehicleType.SHIP,
        itemName = "Furniture",
        amount = 800.0
    ),
    Shipment(
        sentFrom = "Los Angeles, USA",
        sentTo = "Chicago, USA",
        duration = "3 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-3311",
        vehicleType = VehicleType.TRUCK,
        itemName = "Books",
        amount = 220.0
    ),
    Shipment(
        sentFrom = "Cape Town, South Africa",
        sentTo = "Lagos, Nigeria",
        duration = "7 days",
        status = ShipmentStatus.Loading,
        shipmentNumber = "SHIP-9988",
        vehicleType = VehicleType.SHIP,
        itemName = "Steel Pipes",
        amount = 1450.0
    ),
    Shipment(
        sentFrom = "Kuala Lumpur, Malaysia",
        sentTo = "Manila, Philippines",
        duration = "5 days",
        status = ShipmentStatus.Pending,
        shipmentNumber = "PLN-5566",
        vehicleType = VehicleType.PLANE,
        itemName = "Smartphones",
        amount = 720.0
    ),
    Shipment(
        sentFrom = "Hanoi, Vietnam",
        sentTo = "Ho Chi Minh City, Vietnam",
        duration = "1 day",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-3344",
        vehicleType = VehicleType.TRUCK,
        itemName = "Stationery",
        amount = 180.0
    ),
    Shipment(
        sentFrom = "Athens, Greece",
        sentTo = "Istanbul, Turkey",
        duration = "3 days",
        status = ShipmentStatus.Cancelled,
        shipmentNumber = "TRK-7788",
        vehicleType = VehicleType.TRUCK,
        itemName = "Olive Oil",
        amount = 270.0
    ),
    Shipment(
        sentFrom = "Toronto, Canada",
        sentTo = "Montreal, Canada",
        duration = "2 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-5567",
        vehicleType = VehicleType.TRUCK,
        itemName = "Furniture",
        amount = 610.0
    ),
    Shipment(
        sentFrom = "Moscow, Russia",
        sentTo = "Helsinki, Finland",
        duration = "6 days",
        status = ShipmentStatus.InProgress,
        shipmentNumber = "PLN-9981",
        vehicleType = VehicleType.PLANE,
        itemName = "Laptops",
        amount = 940.0
    ),
    Shipment(
        sentFrom = "Doha, Qatar",
        sentTo = "Riyadh, Saudi Arabia",
        duration = "3 days",
        status = ShipmentStatus.Pending,
        shipmentNumber = "TRK-1045",
        vehicleType = VehicleType.TRUCK,
        itemName = "Luxury Goods",
        amount = 1200.0
    ),
    Shipment(
        sentFrom = "Lima, Peru",
        sentTo = "Bogotá, Colombia",
        duration = "4 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "SHIP-3321",
        vehicleType = VehicleType.SHIP,
        itemName = "Coffee Beans",
        amount = 680.0
    ),
    Shipment(
        sentFrom = "Zurich, Switzerland",
        sentTo = "Vienna, Austria",
        duration = "2 days",
        status = ShipmentStatus.Loading,
        shipmentNumber = "PLN-2134",
        vehicleType = VehicleType.PLANE,
        itemName = "Precision Instruments",
        amount = 890.0
    ),
    Shipment(
        sentFrom = "Kigali, Rwanda",
        sentTo = "Bujumbura, Burundi",
        duration = "1 day",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-4567",
        vehicleType = VehicleType.TRUCK,
        itemName = "Textiles",
        amount = 260.0
    ),
    Shipment(
        sentFrom = "Melbourne, Australia",
        sentTo = "Auckland, New Zealand",
        duration = "9 days",
        status = ShipmentStatus.InProgress,
        shipmentNumber = "SHIP-7890",
        vehicleType = VehicleType.SHIP,
        itemName = "Heavy Equipment",
        amount = 1320.0
    ),
    Shipment(
        sentFrom = "Karachi, Pakistan",
        sentTo = "Tehran, Iran",
        duration = "6 days",
        status = ShipmentStatus.Cancelled,
        shipmentNumber = "TRK-3099",
        vehicleType = VehicleType.TRUCK,
        itemName = "Plastic Products",
        amount = 470.0
    ),
    Shipment(
        sentFrom = "Baghdad, Iraq",
        sentTo = "Amman, Jordan",
        duration = "3 days",
        status = ShipmentStatus.Completed,
        shipmentNumber = "TRK-4099",
        vehicleType = VehicleType.TRUCK,
        itemName = "Medicinal Herbs",
        amount = 520.0
    )
)


val itemCategories = listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")

