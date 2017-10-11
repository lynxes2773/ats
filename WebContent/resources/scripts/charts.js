      // Load the Visualization API and the piechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
     google.charts.setOnLoadCallback(drawChart);
      
      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart(dormant, applied, interviewsScheduled, contactInitiated) 
      { 
    	  window.alert("Sum: "+dormant+applied+interviewsScheduled+contactInitiated);
          // Create our data table.
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Status');
          data.addColumn('number', 'Position Count');
//          data.addRows([
//            ['Dormant', dormant],
//            ['Applied', applied],
//            ['Interviews Scheduled', interviewsScheduled],
//            ['Contact Initiated', contactInitiated],
//          ]);         
          data.addRows([
              ['Dormant', 23],
              ['Applied', 34],
              ['Interviews Scheduled', 45],
              ['Contact Initiated', 56],
            ]);        
          // Set chart options
          var options = {'title':'Application Status',
                       'width':500,
                       'height':400};
    	  
          // Instantiate and draw our chart, passing in some options.
          var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
          google.visualization.events.addListener(chart, 'select', selectHandler);
          chart.draw(data, options);
      }

      function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        var value = data.getValue(selectedItem.row, 0);
        alert('The user selected ' + value);
      }
