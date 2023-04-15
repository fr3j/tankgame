module waterworld {
    requires transitive hanyaeger;

    exports com.github.hanyaeger.tutorial;
    exports com.github.hanyaeger.tutorial.scenes;

    opens audio;
    opens backgrounds;
    opens sprites;
    exports com.github.hanyaeger.tutorial.entities.map;
    exports com.github.hanyaeger.tutorial.entities;
}
