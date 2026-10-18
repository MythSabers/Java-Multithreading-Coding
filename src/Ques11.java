
/*
    Ques11 - Use CompletableFuture to combine two API results	Async composition
* */

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Ques11 {

    private HashMap<Object, Object> fetchUserProfile(String userId) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("USER_ID", userId);
        map.put("NAME", "SWAPNIL");
        return map;
    }

    private HashMap<Object, Object> fetchCreditScore(String userId) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("USER_ID", userId);
        map.put("SCORE", "777");
        return map;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Ques11 obj = new Ques11();
        CompletableFuture<HashMap<Object, Object>> userDashboardData = obj.getUserDashboardData("123");
        System.out.println(userDashboardData.get());

    }

    public CompletableFuture<HashMap<Object, Object>> getUserDashboardData(String userId) {
        CompletableFuture<HashMap<Object, Object>> profileFuture = CompletableFuture.supplyAsync(() -> fetchUserProfile(userId))
                .exceptionally(ex -> {
                    System.err.println("Profile API failed: " + ex.getMessage());
                    return new HashMap<>();
                });

        CompletableFuture<HashMap<Object, Object>> creditFuture = CompletableFuture.supplyAsync(() -> fetchCreditScore(userId))
                .exceptionally(ex -> {
                    System.err.println("Credit API failed: " + ex.getMessage());
                    return new HashMap<>();
                });

        return profileFuture.thenCombine(creditFuture, (profile, credit) -> {
            HashMap<Object, Object> map = new HashMap<>();
            map.putAll(profile);
            map.putAll(credit);
            return map;
        });
    }
}
