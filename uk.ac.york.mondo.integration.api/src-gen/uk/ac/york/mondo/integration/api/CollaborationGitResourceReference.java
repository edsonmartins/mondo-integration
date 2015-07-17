/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package uk.ac.york.mondo.integration.api;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-7-17")
public class CollaborationGitResourceReference implements org.apache.thrift.TBase<CollaborationGitResourceReference, CollaborationGitResourceReference._Fields>, java.io.Serializable, Cloneable, Comparable<CollaborationGitResourceReference> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CollaborationGitResourceReference");

  private static final org.apache.thrift.protocol.TField REPOSITORY_URI_FIELD_DESC = new org.apache.thrift.protocol.TField("repositoryUri", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField BRANCH_FIELD_DESC = new org.apache.thrift.protocol.TField("branch", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField COMMIT_FIELD_DESC = new org.apache.thrift.protocol.TField("commit", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CollaborationGitResourceReferenceStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CollaborationGitResourceReferenceTupleSchemeFactory());
  }

  public String repositoryUri; // required
  public String branch; // required
  public String commit; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    REPOSITORY_URI((short)1, "repositoryUri"),
    BRANCH((short)2, "branch"),
    COMMIT((short)3, "commit");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REPOSITORY_URI
          return REPOSITORY_URI;
        case 2: // BRANCH
          return BRANCH;
        case 3: // COMMIT
          return COMMIT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REPOSITORY_URI, new org.apache.thrift.meta_data.FieldMetaData("repositoryUri", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BRANCH, new org.apache.thrift.meta_data.FieldMetaData("branch", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COMMIT, new org.apache.thrift.meta_data.FieldMetaData("commit", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CollaborationGitResourceReference.class, metaDataMap);
  }

  public CollaborationGitResourceReference() {
  }

  public CollaborationGitResourceReference(
    String repositoryUri,
    String branch,
    String commit)
  {
    this();
    this.repositoryUri = repositoryUri;
    this.branch = branch;
    this.commit = commit;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CollaborationGitResourceReference(CollaborationGitResourceReference other) {
    if (other.isSetRepositoryUri()) {
      this.repositoryUri = other.repositoryUri;
    }
    if (other.isSetBranch()) {
      this.branch = other.branch;
    }
    if (other.isSetCommit()) {
      this.commit = other.commit;
    }
  }

  public CollaborationGitResourceReference deepCopy() {
    return new CollaborationGitResourceReference(this);
  }

  @Override
  public void clear() {
    this.repositoryUri = null;
    this.branch = null;
    this.commit = null;
  }

  public String getRepositoryUri() {
    return this.repositoryUri;
  }

  public CollaborationGitResourceReference setRepositoryUri(String repositoryUri) {
    this.repositoryUri = repositoryUri;
    return this;
  }

  public void unsetRepositoryUri() {
    this.repositoryUri = null;
  }

  /** Returns true if field repositoryUri is set (has been assigned a value) and false otherwise */
  public boolean isSetRepositoryUri() {
    return this.repositoryUri != null;
  }

  public void setRepositoryUriIsSet(boolean value) {
    if (!value) {
      this.repositoryUri = null;
    }
  }

  public String getBranch() {
    return this.branch;
  }

  public CollaborationGitResourceReference setBranch(String branch) {
    this.branch = branch;
    return this;
  }

  public void unsetBranch() {
    this.branch = null;
  }

  /** Returns true if field branch is set (has been assigned a value) and false otherwise */
  public boolean isSetBranch() {
    return this.branch != null;
  }

  public void setBranchIsSet(boolean value) {
    if (!value) {
      this.branch = null;
    }
  }

  public String getCommit() {
    return this.commit;
  }

  public CollaborationGitResourceReference setCommit(String commit) {
    this.commit = commit;
    return this;
  }

  public void unsetCommit() {
    this.commit = null;
  }

  /** Returns true if field commit is set (has been assigned a value) and false otherwise */
  public boolean isSetCommit() {
    return this.commit != null;
  }

  public void setCommitIsSet(boolean value) {
    if (!value) {
      this.commit = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case REPOSITORY_URI:
      if (value == null) {
        unsetRepositoryUri();
      } else {
        setRepositoryUri((String)value);
      }
      break;

    case BRANCH:
      if (value == null) {
        unsetBranch();
      } else {
        setBranch((String)value);
      }
      break;

    case COMMIT:
      if (value == null) {
        unsetCommit();
      } else {
        setCommit((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case REPOSITORY_URI:
      return getRepositoryUri();

    case BRANCH:
      return getBranch();

    case COMMIT:
      return getCommit();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case REPOSITORY_URI:
      return isSetRepositoryUri();
    case BRANCH:
      return isSetBranch();
    case COMMIT:
      return isSetCommit();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CollaborationGitResourceReference)
      return this.equals((CollaborationGitResourceReference)that);
    return false;
  }

  public boolean equals(CollaborationGitResourceReference that) {
    if (that == null)
      return false;

    boolean this_present_repositoryUri = true && this.isSetRepositoryUri();
    boolean that_present_repositoryUri = true && that.isSetRepositoryUri();
    if (this_present_repositoryUri || that_present_repositoryUri) {
      if (!(this_present_repositoryUri && that_present_repositoryUri))
        return false;
      if (!this.repositoryUri.equals(that.repositoryUri))
        return false;
    }

    boolean this_present_branch = true && this.isSetBranch();
    boolean that_present_branch = true && that.isSetBranch();
    if (this_present_branch || that_present_branch) {
      if (!(this_present_branch && that_present_branch))
        return false;
      if (!this.branch.equals(that.branch))
        return false;
    }

    boolean this_present_commit = true && this.isSetCommit();
    boolean that_present_commit = true && that.isSetCommit();
    if (this_present_commit || that_present_commit) {
      if (!(this_present_commit && that_present_commit))
        return false;
      if (!this.commit.equals(that.commit))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_repositoryUri = true && (isSetRepositoryUri());
    list.add(present_repositoryUri);
    if (present_repositoryUri)
      list.add(repositoryUri);

    boolean present_branch = true && (isSetBranch());
    list.add(present_branch);
    if (present_branch)
      list.add(branch);

    boolean present_commit = true && (isSetCommit());
    list.add(present_commit);
    if (present_commit)
      list.add(commit);

    return list.hashCode();
  }

  @Override
  public int compareTo(CollaborationGitResourceReference other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRepositoryUri()).compareTo(other.isSetRepositoryUri());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRepositoryUri()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.repositoryUri, other.repositoryUri);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBranch()).compareTo(other.isSetBranch());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBranch()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.branch, other.branch);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCommit()).compareTo(other.isSetCommit());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCommit()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.commit, other.commit);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("CollaborationGitResourceReference(");
    boolean first = true;

    sb.append("repositoryUri:");
    if (this.repositoryUri == null) {
      sb.append("null");
    } else {
      sb.append(this.repositoryUri);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("branch:");
    if (this.branch == null) {
      sb.append("null");
    } else {
      sb.append(this.branch);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("commit:");
    if (this.commit == null) {
      sb.append("null");
    } else {
      sb.append(this.commit);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (repositoryUri == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'repositoryUri' was not present! Struct: " + toString());
    }
    if (branch == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'branch' was not present! Struct: " + toString());
    }
    if (commit == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'commit' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CollaborationGitResourceReferenceStandardSchemeFactory implements SchemeFactory {
    public CollaborationGitResourceReferenceStandardScheme getScheme() {
      return new CollaborationGitResourceReferenceStandardScheme();
    }
  }

  private static class CollaborationGitResourceReferenceStandardScheme extends StandardScheme<CollaborationGitResourceReference> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CollaborationGitResourceReference struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REPOSITORY_URI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.repositoryUri = iprot.readString();
              struct.setRepositoryUriIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BRANCH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.branch = iprot.readString();
              struct.setBranchIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COMMIT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.commit = iprot.readString();
              struct.setCommitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, CollaborationGitResourceReference struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.repositoryUri != null) {
        oprot.writeFieldBegin(REPOSITORY_URI_FIELD_DESC);
        oprot.writeString(struct.repositoryUri);
        oprot.writeFieldEnd();
      }
      if (struct.branch != null) {
        oprot.writeFieldBegin(BRANCH_FIELD_DESC);
        oprot.writeString(struct.branch);
        oprot.writeFieldEnd();
      }
      if (struct.commit != null) {
        oprot.writeFieldBegin(COMMIT_FIELD_DESC);
        oprot.writeString(struct.commit);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CollaborationGitResourceReferenceTupleSchemeFactory implements SchemeFactory {
    public CollaborationGitResourceReferenceTupleScheme getScheme() {
      return new CollaborationGitResourceReferenceTupleScheme();
    }
  }

  private static class CollaborationGitResourceReferenceTupleScheme extends TupleScheme<CollaborationGitResourceReference> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CollaborationGitResourceReference struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.repositoryUri);
      oprot.writeString(struct.branch);
      oprot.writeString(struct.commit);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CollaborationGitResourceReference struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.repositoryUri = iprot.readString();
      struct.setRepositoryUriIsSet(true);
      struct.branch = iprot.readString();
      struct.setBranchIsSet(true);
      struct.commit = iprot.readString();
      struct.setCommitIsSet(true);
    }
  }

}

