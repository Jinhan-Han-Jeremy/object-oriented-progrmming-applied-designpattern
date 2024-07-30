package org.example.member;

import java.util.List;

public class BusinessLead extends TeamMember {
    public BusinessLead(String name, List<String> skills) {
            super(name, "Business Analyst", skills);
    }

    @Override
    public void performDuties() {
            System.out.println(getName() + " is analyzing business requirements and processes.");
    }

}