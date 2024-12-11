package org.Enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Instance {
    public Instance Parent = null;

    private ArrayList<String> tags = new ArrayList<>();
    private Map<String, Object> attributes = new HashMap<>();

    protected boolean IsService = false;

    public Instance() {
        Universe.instances.add(this);
    }

    public Instance[] GetChildren() {
        ArrayList<Instance> children = new ArrayList<>();
        for (Instance inst : Universe.instances) {
            if (inst.Parent == this) {
                children.add(inst);
            }
        }
        return children.toArray(new Instance[0]);
    }

    public boolean IsDescendantOf(Instance ancestor) {
        Instance current = this;
        while (current!= null) {
            if (current == ancestor) {
                return true;
            }
            current = current.Parent;
        }
        return false;
    }

    public boolean IsAncestorOf(Instance ancestor) {
        return ancestor.IsDescendantOf(this);
    }

    public Instance[] GetDescendants() {
        ArrayList<Instance> descendants = new ArrayList<>();
        for (Instance inst : Universe.instances) {
            if (IsAncestorOf(inst)) {
                descendants.add(inst);
            }
        }
        return descendants.toArray(new Instance[0]);
    }

    public void Destroy() {
        Instance[] descendants = GetDescendants();
        for (Instance descendant : descendants) {
            descendant.Parent = null;
        }
        this.Parent = null;
        this.Destroyed.Fire(null);
    }

    public void AddTag(String tag) {
        tags.add(tag);
        Map<String, Object> values = new HashMap<>();
        values.put("tag", tag);
        this.TagAdded.Fire(values);
    }

    public boolean HasTag(String tag) {
        return tags.contains(tag);
    }

    public void RemoveTag(String tag) {
        tags.remove(tag);
        Map<String, Object> values = new HashMap<>();
        this.TagRemoved.Fire(values);
    }

    public String[] GetTags() {
        return tags.toArray(new String[0]);
    }

    public void SetAttribute(String name, Object value) {
        attributes.put(name, value);
        Map<String, Object> values = new HashMap<>();
        values.put("name", name);
        values.put("value", value);
        this.AttributeChanged.Fire(values);
    }

    public Object GetAttribute(String name) {
        return attributes.get(name);
    }

    public void RemoveAttribute(String name) {
        attributes.remove(name);
    }

    public boolean HasAttribute(String name) {
        return attributes.containsKey(name);
    }

    public Map<String, Object> GetAttributes() {
        return new HashMap<>(attributes);
    }

    public abstract void Tick(double DeltaTime);
    public abstract void PreTick(double DeltaTime);
    public abstract void Render(double DeltaTime);
    public abstract void PreRender(double DeltaTime);

    public final ScriptSignal ChildAdded = new ScriptSignal();
    public final ScriptSignal ChildRemoved = new ScriptSignal();
    public final ScriptSignal ParentChanged = new ScriptSignal();
    public final ScriptSignal AncestorAdded = new ScriptSignal();
    public final ScriptSignal AncestorRemoved = new ScriptSignal();
    public final ScriptSignal AttributeChanged = new ScriptSignal();
    public final ScriptSignal TagAdded = new ScriptSignal();
    public final ScriptSignal TagRemoved = new ScriptSignal();
    public final ScriptSignal Destroyed = new ScriptSignal();
}
