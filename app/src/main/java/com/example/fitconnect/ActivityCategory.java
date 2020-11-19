package com.example.fitconnect;

public enum ActivityCategory {
    WALKING("Walking"),
    BIKING("Biking"),
    RUNNING("Running"),
    DANCING("Dancing"),
    ROLLERBLADING("Rollerblading"),
    ROLLERSKATING("Rollerskating"),
    BASKETBALL("Basketball"),
    FOOTBALL("Football"),
    SOCCER("Soccer"),
    YOGA("Yoga"),
    WEIGHTLIFTING("Weight Lifting"),
    SWIMMING("Swimming"),
    JUMPROPE("Jump Rope"),
    AEROBICS("Aerobics"),
    BALLET("Ballet"),
    PILATES("Pilates"),
    CANOEING("Canoeing"),
    ZUMBA("Zumba"),
    BOXING("Boxing"),
    BOWLING("Bowling"),
    FRISBEE("Frisbee"),
    HIKING("Hiking"),
    HOCKEY("Hockey"),
    ICESKATING("Ice Skating"),
    ROCKCLIMBING("Rock Climbing"),
    HORSEBACKRIDING("Horseback Riding"),
    HULAHOOPING("Hula Hooping"),
    VOLLEYBALL("Volleyball"),
    SOFTBALL("Softball"),
    SKATEBOARDING("Skateboarding"),
    PINGPONG("Ping-Pong");

    private final String label;

    ActivityCategory(String label){
        this.label=label;
    }

    public String getLabel(){
        return this.label;
    }

}
