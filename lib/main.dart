import 'dart:async';

import "package:flutter/material.dart";

void main() => runApp(MyApp());


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: MyBody(),
      ),
    );
  }
}

class MyBody extends StatefulWidget {
  @override
  _MyBodyState createState() => _MyBodyState();
}

class _MyBodyState extends State<MyBody> {
  
  int num = 3;
  
  Timer timer;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    timer = Timer.periodic(Duration(milliseconds: 1000), (value){
      setState(() {
        num --;
      });
      if(num == 0){
        timer.cancel();
      }
    });
  }


  @override
  void dispose() {
    timer.cancel();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Text(num.toString()),
      ),
    );
  }
}

