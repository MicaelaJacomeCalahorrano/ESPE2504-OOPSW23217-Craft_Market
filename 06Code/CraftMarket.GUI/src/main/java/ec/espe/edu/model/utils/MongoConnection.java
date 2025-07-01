/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.model.utils;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class MongoConnection {
      public static MongoDatabase connect() {
        String uri ="mongodb+srv://machicaiza22:pan@cluster0.dqlf2xq.mongodb.net/?retryWrites=true&w=majority";
        MongoClient client = MongoClients.create(uri);
        return client.getDatabase("craftmarket");
    }
}
