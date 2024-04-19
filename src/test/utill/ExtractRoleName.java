package com.rs.listing.Restuarant.listing.utill;

public class ExtractRoleName {

    public static String getRolename(String role) {
        int nameIndex = role.indexOf("name=");

        // Find the start index of the role name
        int startIndex = nameIndex + 5; // "name=".length()

        // Find the end index of the role name (assuming it ends with a comma)
        int endIndex = role.indexOf(",", startIndex);

        // Extract the role name substring
        String roleName = role.substring(startIndex, endIndex);

        // Return the extracted role name
        return roleName;
    }
}
