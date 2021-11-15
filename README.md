# SpirographCreator

GUI created using Swing and AWT (outdated, I know)

Overview:
- Window: sets up JFrame
- Hypocycloid: models hypocycloid
- Panel: sets up JPanel and GUI compoenents and repaints JPanel as coordinates update
- Timer runs at set intervals which updates coordinates and causes panel to be repainted with these new coordinates
- User customisation options: pause drawing, play drawing, slow down, speed up, change pen colour, change background colour, hide controls

Issues:
- Rounding for int coordinates makes some curves look slightly jagged
- Certain parameters cause individual coordinates points to be separated, rather than a smooth line
- GUI is pretty visually unappealing but difficult to customise


Example hypocycloids and their parameters:
! [] (https://user-images.githubusercontent.com/92796525/141858723-8cec5e18-24e0-4e5b-aeed-9076a9f680e1.png)

Experimenting with overlaying and colours:
