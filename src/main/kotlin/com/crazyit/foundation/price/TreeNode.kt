package com.crazyit.foundation.price

import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeType

/**
 * @author Zack
 * Created  on 2017/3/20.
 */
open class TreeNode{
    var  children : List<TreeNode>?
    var  nodeId:Long
    var name:String
    var nodeType:String

    constructor(children:List<TreeNode>,nodeId:Long,name:String,nodeType:String){
        this.children = children;
        this.nodeId = nodeId;
        this.name = name;
        this.nodeType = nodeType
    }



}
