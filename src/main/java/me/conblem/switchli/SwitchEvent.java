package me.conblem.switchli;

public interface SwitchEvent<ViewEnumType extends Enum<ViewEnumType> & ViewEnum> {
    public ViewEnumType getViewEnum();
}
